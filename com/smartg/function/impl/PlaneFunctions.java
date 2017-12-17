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
package com.smartg.function.impl;

import com.smartg.function.IPlaneFunction;
import com.smartg.function.misc.SPoint;

public class PlaneFunctions {

    public static class Identity implements IPlaneFunction {

	public void compute(double x, double y, SPoint dest) {
	    dest.x = x;
	    dest.y = y;
	}
    }

    public static class Sine_XY implements IPlaneFunction {

	public void compute(double x, double y, SPoint dest) {
	    dest.x = Math.sin(PID2 * x);
	    dest.y = Math.sin(PID2 * y);
	}
    }

    public static class Sine_R implements IPlaneFunction {

	public void compute(double x, double y, SPoint dest) {
	    double r = Math.sqrt(x * x + y * y);
	    double theta = Math.atan2(y, x);

	    double rnew = Math.sin(PID2 * r);
	    dest.x = rnew * Math.cos(theta);
	    dest.y = rnew * Math.sin(theta);
	}
    }

    public static class Square_XY implements IPlaneFunction {

	public void compute(double x, double y, SPoint dest) {
	    double x2 = x * x;
	    double y2 = y * y;
	    if (x < 0) {
		x2 = -x2;
	    }
	    if (y < 0) {
		y2 = -y2;
	    }
	    dest.x = x2;
	    dest.y = y2;
	}
    }

    public static class Square_R implements IPlaneFunction {

	public void compute(double x, double y, SPoint dest) {
	    double r = Math.sqrt(x * x + y * y);
	    double theta = Math.atan2(y, x);

	    double rnew = r * r;
	    dest.x = rnew * Math.cos(theta);
	    dest.y = rnew * Math.sin(theta);
	}
    }

    public static class Asin_XY implements IPlaneFunction {

	public void compute(double x, double y, SPoint dest) {
	    dest.x = Math.asin(x) / PID2;
	    dest.y = Math.asin(y) / PID2;
	}
    }

    public static class Asin_R implements IPlaneFunction {

	public void compute(double x, double y, SPoint dest) {
	    double r = Math.sqrt(x * x + y * y);
	    double theta = Math.atan2(y, x);

	    double rnew = Math.asin(r / 1.42) / PID2;
	    dest.x = (rnew * Math.cos(theta));
	    dest.y = (rnew * Math.sin(theta));
	}
    }

    public static class Polar implements IPlaneFunction {

	public void compute(double x, double y, SPoint dest) {
	    dest.x = Math.log(Math.sqrt(x * x + y * y));
	    dest.y = Math.atan2(x, y);
	}
    }

    public static class Fisheye implements IPlaneFunction {

	public void compute(double x, double y, SPoint dest) {
	    double r = Math.sqrt(x * x + y * y);
	    r = Math.atan2(r, 0.3) / (PID2 * 0.9);
	    double phi = Math.atan2(y, x);
	    dest.x = r * Math.cos(phi);
	    dest.y = r * Math.sin(phi);
	}
    }

    public static class Octagon implements IPlaneFunction {

	public void compute(double x, double y, SPoint dest) {
	    double phi = Math.atan2(y, x);
	    dest.x = Math.abs(x) * Math.cos(phi);
	    dest.y = Math.abs(y) * Math.sin(phi);
	}
    }

    public static class ConcaveFisheye implements IPlaneFunction {

	public void compute(double x, double y, SPoint dest) {
	    double r = Math.sqrt(x * x + y * y);

	    double z = PID2 - Math.PI * r;
	    r = Math.atan2(r, z) / Math.PI;
	    double phi = Math.atan2(y, x);
	    dest.x = r * Math.cos(phi);
	    dest.y = r * Math.sin(phi);
	}
    }

    public static class ConcaveFisheye2 implements IPlaneFunction {

	public void compute(double x, double y, SPoint dest) {
	    double r0 = x * x + y * y;
	    double r = Math.sqrt(r0);

	    double z = 0.3 - Math.PI * r0;
	    r = Math.atan2(r, z) / Math.PI;
	    double phi = Math.atan2(y, x);
	    dest.x = r * Math.cos(phi);
	    dest.y = r * Math.sin(phi);
	}
    }

    public static class Clover implements IPlaneFunction {

	public void compute(double x, double y, SPoint dest) {
	    double r0 = x * x + y * y;
	    double r = Math.sqrt(r0);
	    double phi = Math.atan2(y, x);

	    double z = 0.5 - Math.sqrt((r - x) * (r - y) * (r + x) * (r + y) * Math.PI) * 4;
	    r = Math.atan2(r, z) / Math.PI;
	    dest.x = r * Math.cos(phi);
	    dest.y = r * Math.sin(phi);
	}
    }

    public static class Crux implements IPlaneFunction {

	public void compute(double x, double y, SPoint dest) {
	    double x2 = x * x;
	    double y2 = y * y;
	    double r0 = x2 + y2;
	    double r = Math.sqrt(r0);

	    double z = 0.5 - Math.PI * r * r * r * (x2 - y2) * (x2 - y2);
	    r = Math.atan2(Math.sqrt(x2 + y2), z) / Math.PI;
	    double phi = Math.atan2(y, x);
	    dest.x = r * Math.cos(phi);
	    dest.y = r * Math.sin(phi);
	}
    }

    public static class Asin_T implements IPlaneFunction {

	public void compute(double x, double y, SPoint dest) {
	    double x2 = x * x;
	    double y2 = y * y;
	    double r0 = x2 + y2;
	    double r = Math.sqrt(r0);
	    double theta = Math.atan2(y, x);

	    double rnew = Math.asin(Math.abs((r - 2) / 2)) / PID2;
	    dest.x = rnew * Math.cos(theta);
	    dest.y = rnew * Math.sin(theta);
	}
    }
}
