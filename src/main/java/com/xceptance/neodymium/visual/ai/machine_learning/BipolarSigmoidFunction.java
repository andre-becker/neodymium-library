// Copyright © Diego Catalano, 2015
// diego.catalano at live.com
//
// Copyright © Andrew Kirillov, 2007-2008
// andrew.kirillov at gmail.com
//
//    This library is free software; you can redistribute it and/or
//    modify it under the terms of the GNU Lesser General Public
//    License as published by the Free Software Foundation; either
//    version 2.1 of the License, or (at your option) any later version.
//
//    This library is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
//    Lesser General Public License for more details.
//
//    You should have received a copy of the GNU Lesser General Public
//    License along with this library; if not, write to the Free Software
//    Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
//
// Copyright 2017 Thomas Volkmann
//
// Permission is hereby granted, free of charge, to any person obtaining a copy of this
// software and associated documentation files (the "Software"), 
// to deal in the Software without restriction, including without limitation the rights 
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
// and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in all 
// copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, 
// EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS 
// BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, 
// ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

package com.xceptance.neodymium.visual.ai.machine_learning;

import java.io.Serializable;

/**
 * Bipolar sigmoid activation function.
 * 
 * @author Diego Catalano edited by Thomas Volkmann
 */
public class BipolarSigmoidFunction implements IActivationFunction, Serializable
{
    /**
     * Auto generated serial number.
     */
    private static final long serialVersionUID = 1L;

    private double alpha = 2;

    /**
     * Initializes a new instance of the BipolarSigmoidFunction class.
     */
    public BipolarSigmoidFunction()
    {
    }

    /**
     * Initializes a new instance of the BipolarSigmoidFunction class.
     * 
     * @param alpha
     *            Sigmoid's alpha value.
     */
    public BipolarSigmoidFunction(double alpha)
    {
        this.alpha = alpha;
    }

    /**
     * Get Sigmoid's alpha value. The value determines steepness of the function. Increasing value of this property
     * changes sigmoid to look more like a threshold function. Decreasing value of this property makes sigmoid to be
     * very smooth (slowly growing from its minimum value to its maximum value).
     * 
     * @return Alpha value.
     */
    public double getAlpha()
    {
        return alpha;
    }

    /**
     * Set Sigmoid's alpha value. The value determines steepness of the function. Increasing value of this property
     * changes sigmoid to look more like a threshold function. Decreasing value of this property makes sigmoid to be
     * very smooth (slowly growing from its minimum value to its maximum value).
     * 
     * @param alpha
     *            Alpha value.
     */
    public void setAlpha(double alpha)
    {
        this.alpha = alpha;
    }

    /***
     * Calculate the value for the typical sigmoid function.
     */
    @Override
    public double Function(double x)
    {
        return ((2 / (1 + Math.exp(-alpha * x))) - 1);
    }

    /***
     * First derivative for value x.
     */
    @Override
    public double Derivative(double x)
    {
        double y = Function(x);
        return (alpha * (1 - y * y) / 2);
    }

    /***
     * Second derivative for value x.
     */
    @Override
    public double Derivative2(double y)
    {
        return (alpha * (1 - y * y) / 2);
    }
}