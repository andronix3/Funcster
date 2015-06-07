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

public class LogFunction extends Function {

    private float radiusX_2, radiusY_2;
    private float max;

    private float radiusX, radiusY;
    private float s;

    public LogFunction(int radius) {
	this(radius, radius, (float) Math.PI);
    }

    public LogFunction(int radius, float s) {
	this(radius, radius, s);
    }

    public LogFunction(int radiusX, int radiusY) {
	this(radiusX, radiusY, (float) Math.PI);
    }

    public LogFunction(int radiusX, int radiusY, float s) {
	super(Range.create(2, 0, 1), Range.create(1, 0, 1));
	this.s = s;
	this.radiusX = radiusX;
	this.radiusY = radiusY;

	radiusX_2 = (float) (radiusX / Math.sqrt(2));
	radiusY_2 = (float) (radiusY / Math.sqrt(2));

	max = (float) Math.sqrt(radiusX_2 * radiusX_2 + radiusY_2 * radiusY_2);
    }

    @Override
    public void compute(float[] output, float... input) {
	float x = input[0];
	float y = input[1];

	float dx = radiusX - x;
	float dy = radiusY - y;

	float f = (float) Math.sqrt(dx * dx + dy * dy);
	float res = 0f;
	if (f < max) {
	    res = (s + (float) Math.log(1 - f / max)) * 255 / s;
	    if (res < 0) {
		res = 0;
	    }
	}
	output[0] = res / 255.0f;
    }
}
