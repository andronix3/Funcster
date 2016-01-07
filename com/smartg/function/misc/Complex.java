/*
 * Copyright (c) Andrey Kuznetsov. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  o Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  o Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  o Neither the name of imagero Andrey Kuznetsov nor the names of
 *    its contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.smartg.function.misc;

import java.awt.geom.Point2D;

public class Complex {
    public double re;
    public double im;

    public Complex() {
	this.re = 0;
	this.im = 0;
    }

    public Complex(double re, double im) {
	this.re = re;
	this.im = im;
    }

    public Complex(Complex input) {
	this.re = input.re;
	this.im = input.im;
    }

    public Complex(Point2D p) {
	setFromPoint(p.getX(), p.getY());
    }

    public Complex getConjugate() {
	return new Complex(this.re, -this.im);
    }

    public void getConjugate(Complex dest) {
	dest.re = this.re;
	dest.im = -this.im;
    }

    public Complex add(Complex op) {
	return new Complex(this.re + op.re, this.im + op.im);
    }

    public void add(Complex op, Complex dest) {
	double r = this.re + op.re;
	double i = this.im + op.im;
	dest.re = r;
	dest.im = i;
    }

    public void mul(double d, Complex dest) {
	dest.re = re * d;
	dest.im = im * d;
    }

    public void imul(Complex dest) {
	dest.re = -im;
	dest.im = re;
    }

    public Complex sub(Complex op) {
	return new Complex(this.re - op.re, this.im - op.im);
    }

    public void sub(Complex op, Complex dest) {
	double r = this.re - op.re;
	double i = this.im - op.im;
	dest.re = r;
	dest.im = i;
    }

    public Complex mul(Complex op) {
	return new Complex(this.re * op.re - this.im * op.im, this.re * op.im + this.im * op.re);
    }

    public void mul(Complex op, Complex dest) {
	double r = this.re * op.re - this.im * op.im;
	double i = this.re * op.im + this.im * op.re;
	dest.re = r;
	dest.im = i;
    }

    public Complex div(Complex op) {
	Complex result = this.mul(op.getConjugate());
	double opNormSq = op.re * op.re + op.im * op.im;
	result.re = result.re / opNormSq;
	result.im = result.im / opNormSq;
	return result;
    }

    public void div(Complex op, Complex dest) {
	double opNormSq = op.re * op.re + op.im * op.im;
	mul(op.getConjugate(), dest);
	dest.re /= opNormSq;
	dest.im /= opNormSq;
    }

    public void exp(Complex dest) {
	double expReal = Math.exp(re);
	dest.re = expReal * Math.cos(im);
	dest.im = expReal * Math.sin(im);
    }

    public void sin(Complex dest) {
	dest.re = Math.sin(re) * Math.cosh(im);
	dest.im = Math.cos(re) * Math.sinh(im);
    }

    public void cos(Complex dest) {
	dest.re = Math.cos(re) * Math.cosh(im);
	dest.im = -Math.sin(re) * Math.sinh(im);
    }

    public double abs() {
	if (Math.abs(re) < Math.abs(im)) {
	    if (im == 0.0) {
		return Math.abs(re);
	    }
	    double q = re / im;
	    return Math.abs(im) * Math.sqrt(1 + q * q);
	}
	if (re == 0.0) {
	    return Math.abs(im);
	}
	double q = im / re;
	return Math.abs(re) * Math.sqrt(1 + q * q);
    }

    public void sqrt(Complex dest) {
	double t = Math.sqrt((Math.abs(re) + abs()) / 2.0);
	if (re >= 0.0) {
	    dest.re = t;
	    dest.im = im / (2.0 * t);
	} else {
	    dest.re = Math.abs(im) / (2.0 * t);
	    dest.im = Math.copySign(1d, im) * t;
	}
    }

    public void setFromPoint(double a, double b) {
	double c = Math.sqrt(a * a + b * b);
	double cosAlpha = b / c;
	double angle = Math.acos(cosAlpha);
	re = c * Math.cos(angle);
	im = c * Math.sin(angle);
    }

    public void toPoint(Point2D dest) {
	double m = getMagnitude();
	double angle = getAngle();
	double bc = Math.cos(angle);
	double b = bc * m;
	double a = Math.sqrt(m * m - b * b);
	dest.setLocation(a, b);
    }

    public Complex fromPolar(double magnitude, double angle) {
	return new Complex(magnitude * Math.cos(angle), magnitude * Math.sin(angle));
    }

    public void fromPolar(double magnitude, double angle, Complex dest) {
	dest.re = magnitude * Math.cos(angle);
	dest.im = magnitude * Math.sin(angle);
    }

    public double getMagnitude() {
	return Math.sqrt(this.re * this.re + this.im * this.im);
    }

    public double getAngle() {
	return Math.atan2(this.im, this.re);
    }

    public String toString() {
	if (this.re == 0) {
	    if (this.im == 0) {
		return "0";
	    }
	    return (this.im + "i");
	}
	if (this.im == 0) {
	    return String.valueOf(this.re);
	} else if (this.im < 0) {
	    return (this.re + " " + this.im + "i");
	} else {
	    return (this.re + " +" + this.im + "i");
	}
    }
}
