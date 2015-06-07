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
package com.smartg.function;


/**
 * Helper for complex functions.
 * Allows to zoom in/out.
 * 
 * @author andrey
 *
 */
public class ComplexMagnify implements ComplexFunction {

    float width, height;
    Complex c = new Complex();
    Complex tmp = new Complex();
    ComplexFunction f;

    double rotate = Math.PI + 0.01;
    double ix;
    double iy;

    double vx = -1;
    double vy = -1;

    float w;
    float h;

    double magnify = 1000;
    
    public ComplexMagnify(ComplexFunction f) {
	this.f = f;
    }

    /**
     * 
     * @param width
     *            source area width
     * @param height
     *            source area height
     * @param f
     *            complex function
     * @param magnify
     *            zoom factor
     */
    public ComplexMagnify(float width, float height, ComplexFunction f, double magnify) {
	this(Math.PI + 0.01, width, height, f, magnify);
    }

    public ComplexMagnify(double rotate, float width, float height, ComplexFunction f, double magnify) {
	this.width = width;
	this.height = height;
	this.f = f;
	this.magnify = magnify;
	w = width;
	h = height;

	ix = 1.0 / width * rotate;
	iy = 1.0 / height * rotate;

    }

    public double getRotate() {
	return rotate;
    }

    public void setRotate(double m) {
	this.rotate = m;
	ix = 1.0 / width * m;
	iy = 1.0 / height * m;
    }

    public double getMagnify() {
	return magnify;
    }

    public void setMagnify(double magnify) {
	this.magnify = magnify;
    }

    public void compute(Complex dest, Complex cn) {
	double x = cn.re * 2;
	double y = cn.im * 2;

	vy = -1 * iy * y;
	vx = -1 * ix * x;

	c.re = vx;
	c.im = vy;

	f.compute(tmp, c);

	double kx = width + ((tmp.re / Math.PI) / 2.0) * magnify;
	double ky = height + ((tmp.im / Math.PI) / 2.0) * magnify;

	dest.re = kx;
	dest.im = ky;
    }
}