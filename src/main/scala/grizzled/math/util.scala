/*
  ---------------------------------------------------------------------------
  This software is released under a BSD license, adapted from
  http://opensource.org/licenses/bsd-license.php

  Copyright © 2009-2016, Brian M. Clapper
  All rights reserved.

  Redistribution and use in source and binary forms, with or without
  modification, are permitted provided that the following conditions are
  met:

   * Redistributions of source code must retain the above copyright notice,
    this list of conditions and the following disclaimer.

   * Redistributions in binary form must reproduce the above copyright
    notice, this list of conditions and the following disclaimer in the
    documentation and/or other materials provided with the distribution.

   * Neither the names "clapper.org", "Grizzled Scala Library", nor the
    names of its contributors may be used to endorse or promote products
    derived from this software without specific prior written permission.

  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
  IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
  THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
  PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
  EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
  PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
  PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
  LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
  ---------------------------------------------------------------------------
*/

package grizzled.math

/** Useful math-related utility functions.
  */
object util {

  /** A `max` function that will take any number of arguments for which an
    * `Ordering` is defined, returning the "largest" one, as defined by the
    * `Ordering`.
    *
    * @param args  the items for which to find the maximum
    * @tparam T    the argument type
    *
    * @return the maximum value
    */
  def max[T: Ordering](args: T*): T = {
    args.reduce { (a: T, b: T) =>
      val ev = implicitly[Ordering[T]]
      ev.compare(a, b) match {
        case i if i < 0 => b
        case i if i > 0 => a
        case _          => a
      }
    }
  }

  /** A `max` function that will take any number of arguments for which an
    * `Ordering` is defined, returning the "largest" one, as defined by the
    * `Ordering`.
    *
    * @param args  the items for which to find the maximum
    * @tparam T    the argument type
    *
    * @return the maximum value
    */
  def min[T: Ordering](args: T*): T = {
    args.reduce { (a: T, b: T) =>
      val ev = implicitly[Ordering[T]]
      ev.compare(a, b) match {
        case i if i < 0 => a
        case i if i > 0 => b
        case _          => a
      }
    }
  }
}
