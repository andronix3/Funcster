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
import com.smartg.function.misc.Range;

/**
 * ExponentialFunction defines an exponential interpolation of one input value
 * and n output values:
 * 
 * @author andrey
 * 
 */
public class ExponentialFunction extends Function {

	private float N;
	private float[] C0;
	private float[] C1;
	private float[] C2;

	public ExponentialFunction(Range[] domain, Range[] range, float n) {
		this(domain, range, n, new float[] { 0 }, new float[] { 1 });
	}

	/**
	 * 
	 * @param domain
	 *            An array of 2 numbers (domain[0] < [domain[1]). Input values
	 *            outside the declared domain are clipped to the nearest
	 *            boundary value.
	 * @param range
	 *            An array of 2  n numbers, where n is the number of output
	 *            values. For each j from 0 to n - 1, Range[2j] must be less
	 *            than or equal to Range[2j+1] , and the jth output value, yj ,
	 *            must lie in the interval Range[2j] = yj = Range[2j+1] . Output
	 *            values outside the declared range are clipped to the nearest
	 *            boundary value.
	 * @param n
	 *            The interpolation exponent.
	 * @param c0
	 *            An array of n numbers defining the function result when x =
	 *            0.0.
	 * @param c1
	 *            An array of n numbers defining the function result when x =
	 *            1.0.
	 */
	public ExponentialFunction(Range[] domain, Range[] range, float n,
			float[] c0, float[] c1) {
		super(domain, range);
		setN(n);
		setC0(c0);
		setC1(c1);
	}

	public int getNumInputs() {
		return 1;
	}

	public int getNumOutputs() {
		int outputCount = 0;
		if (C0 != null) {
			outputCount = C0.length;
		} else if (range != null) {
			outputCount = range.length / 2;
		} else {
			outputCount = 1;
		}
		return outputCount;
	}

	void setN(float nValue) {
		N = nValue;
	}

	void setC1(float[] c1Values) {
		C1 = c1Values;
		C2 = new float[C1.length];
		for (int i = 0; i < C2.length; i++) {
			C2[i] = C1[i] - C0[i];
		}
	}

	void setC0(float[] c0Values) {
		C0 = c0Values;
	}

	public void compute(float[] output, float ... input) {
		float x = input[0];

		int outputCount = getNumOutputs();

		float p = (float) Math.pow(x, N);
		for (int j = 0; j < outputCount; j++) {
			output[j] = C0[j] + p * C2[j];
		}
	}
}