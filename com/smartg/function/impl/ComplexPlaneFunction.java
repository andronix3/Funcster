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

import com.smartg.function.IComplexFunction;
import com.smartg.function.IPlaneFunction;
import com.smartg.function.misc.Complex;
import com.smartg.function.misc.DPoint;

/**
 * Wrapper for ComplexFunction. Just pass point(x, y) as complex number to
 * complex function.
 * 
 * @author andrey
 * 
 */
public class ComplexPlaneFunction implements IPlaneFunction {

    private IComplexFunction function;
    private Complex complex = new Complex();
    private Complex dst = new Complex();

    public ComplexPlaneFunction(IComplexFunction function) {
	this.function = function;
    }

    public void compute(double x, double y, DPoint dest) {
	complex.re = x;
	complex.im = y;
	function.compute(dst, complex);
	dest.x = dst.re;
	dest.y = dst.im;
    }

    public IComplexFunction getFunction() {
	return function;
    }

    public void setFunction(IComplexFunction function) {
	this.function = function;
    }
}
