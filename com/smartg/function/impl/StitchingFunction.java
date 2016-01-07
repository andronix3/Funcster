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

import com.smartg.function.Function;
import com.smartg.function.IFunction;
import com.smartg.function.misc.Range;

/**
 * 
 * @author andrey
 * 
 */
public class StitchingFunction extends Function {

    private IFunction[] functions;
    private float[] bounds;
    private float[] encode;
    private float[] subdomains;

    public StitchingFunction(IFunction[] functions, Range[] domain, Range[] range, float[] bounds, float[] encode) {
	super(domain, range);
	this.functions = functions;
	setEncode(encode);
	setBounds(bounds);
    }

    void setEncode(float[] encodeValues) {
	encode = encodeValues;
    }

    void setBounds(float[] boundsValues) {
	bounds = boundsValues;
	subdomains = new float[functions.length + 1];
	subdomains[0] = domain[0].min;
	for (int i = 0; i < bounds.length; i++) {
	    subdomains[i + 1] = bounds[i];
	}
	subdomains[subdomains.length - 1] = domain[0].max;
    }

    int getSubdomain(float d) {
	for (int i = 0; i < subdomains.length - 1; i++) {
	    if (d >= subdomains[i] && d < subdomains[i + 1]) {
		return i;
	    }
	}
	return -1;
    }

    void encode(int i, float[] input) {
	float x = input[0];
	float xp = interpolate(x, subdomains[i], subdomains[i + 1], encode[i + i], encode[i + i + 1]);
	input[0] = xp;
    }

    public void compute(float[] output, float... input) {
	int i = getSubdomain(input[0]);
	if (i > -1) {
	    encode(i, input);
	    functions[i].compute(output, input);
	}
    }
}