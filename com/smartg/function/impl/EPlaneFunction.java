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
import com.smartg.function.impl.PlaneFunctions.*;

public enum EPlaneFunction {
    IDENTITY(new Identity()),
    SINE_XY(new Sine_XY()),
    SINE_R(new Sine_R()),
    SQUARE_XY(new Square_XY()),
    SQUARE_R(new Square_R()),
    ASIN_XY(new Asin_XY()),
    ASIN_R(new Asin_R()),
    POLAR(new Polar()),
    FISHEYE(new Fisheye()),
    OCTAGON(new Octagon()),
    CONCAVE_FISHEYE(new ConcaveFisheye()),
    CONCAVE_FISHEYE2(new ConcaveFisheye2()),
    CLOVER(new Clover()),
    CRUX(new Crux()),
    ASIN_T(new Asin_T())
   
    ;

    private EPlaneFunction(IPlaneFunction planeFunction) {
	this.planeFunction = planeFunction;
    }

    IPlaneFunction planeFunction;

    public IPlaneFunction getPlaneFunction() {
	return planeFunction;
    }

}
