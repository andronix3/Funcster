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
 * IFunction represent parameterized classes of functions, like mathematical
 * formulas or sampled representations. In general, a function can take any
 * number of input values and produce any number of output values. Function must
 * define domain, the set of legal values for the input. Function may also
 * define a range, the set of legal values for the output. Input values passed
 * to the function are clipped to the domain, and output values produced by the
 * function are clipped to the range.
 * 
 * @author andrey
 * 
 */
public interface IFunction {

    void compute(float[] output, float ... input);

    int getNumInputs();

    int getNumOutputs();

    /**
     * An array of 2 × m numbers, where m is the number of input values.
     * For each i from 0 to m - 1 the ith input value must be in interval from domain[i * 2] to domain[i * 2 + 1].
     * Input values outside the declared domain are clipped to the nearest boundary value.
     * 
     * @return
     */
    Range[] getInputDomain();

    /**
     * An array of 2 × n numbers, where n is the number of output values.
     * For each j from 0 to n - 1 the jth output value,
     * must be in the interval from range[2 * j] to range[2 * j + 1].
     * Output values outside the declared range are clipped to the nearest boundary value.
     * 
     * @return range
     */
    Range[] getOutputRange();
}