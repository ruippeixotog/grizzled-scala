package grizzled.math

import grizzled.math.stats._

import Numeric._
import java.lang.Math.sqrt

import grizzled.BaseSpec

/**
 * Tests the grizzled.file functions.
 */
class StatsSpec extends BaseSpec {
  private def dList[T](l: T*)(implicit x: Numeric[T]): List[Double] =
    l.map (x.toDouble).toList

  "geometric mean" should "produce proper values" in {
    val Data = List(
      (8.15193109605923,   dList(1, 10, 30, 10, 12)),
      (5.78182338862232,   dList(1.0, 10.0, 30.0, 10.0, 12.0, 2.0, 3.0)),
      (12.04449703813164,  dList(1 to 30: _*)),
      (100.0,              List(100.0))
    )

    for ((expected, values) <- Data) {
      geometricMean(values: _*) shouldBe expected
    }
  }

  "harmonic mean" should "produce proper values" in {
    val Data = List(
      (3.797468354430379,  dList(1, 10, 30, 10, 12)),
      (3.2558139534883717, dList(1.0, 10.0, 30.0, 10.0, 12.0, 2.0, 3.0)),
      (7.509410923456069,  dList(1 to 30: _*)),
      (100.0,              List(100.0))
    )

    for ((expected, values) <- Data) {
      harmonicMean(values: _*) shouldBe expected
    }
  }

  "arithmetic mean" should "produce proper values" in {
    val Data = List(
      (12.6,              dList(1, 10, 30, 10, 12)),
      (9.714285714285714, dList(1.0, 10.0, 30.0, 10.0, 12.0, 2.0, 3.0)),
      (15.5,              dList(1 to 30: _*)),
      (100.0,             dList(100, 150, 50)),
      (100.0,             List(100.0))
    )

    for ((expected, values) <- Data) {
      mean(values: _*) shouldBe expected
    }
  }

  "median" should "produce proper values" in {
    val Data = List(
      (10.0,  dList(1, 10, 30, 10, 12)),
      (10.0,  dList(1.0, 10.0, 30.0, 10.0, 12.0, 2.0, 3.0)),
      (15.5,  dList(1 to 30: _*)),
      (100.0, dList(100, 150, 50)),
      (2.0,   dList(1, 1, 1, 2, 10, 30, 1000)),
      (16.0,  dList(2, 2, 2, 2, 2, 30, 30, 30, 30, 30))

    )

    for ((expected, values) <- Data) {
      median(values: _*) shouldBe expected
    }
  }

  "mode" should "produce proper values" in {
    val Data = List(
      (dList(10),          dList(1, 10, 30, 10, 12)),
      (dList(1),           dList(1, 10, 3, 1, 100)),
      (dList(1, 3),        dList(1, 2, 3, 1, 3)),
      (dList(1, 3, 1000),  dList(1, 2, 3, 1, 3, 1000, 1000, 9)),
      (dList(1),           dList(1))
    )

    for ((expected, values) <- Data) {
      mode(values: _*).sortWith(_ < _) shouldBe expected
    }
  }

  "sample variance" should "produce proper values" in {
    val Data = List(
      (50.0,       dList(10, 20)),
      (1866.5,     dList(1, 10, 3, 1, 100)),
      (1.0,        dList(1, 2, 3, 1, 3)),
      (100.0,      dList(10.5, 20.5, 30.5)),
      (212937.125, dList(1, 2, 3, 1, 3, 1000, 1000, 9))
    )

    for ((expected, values) <- Data) {
      sampleVariance(values: _*) shouldBe expected
    }
  }

  private val SampleStddevData = List(
    (7.0710678118654755, dList(10, 20)),
    (43.2030091544559,   dList(1, 10, 3, 1, 100)),
    (1.0,                dList(1, 2, 3, 1, 3)),
    (461.45110791935474, dList(1, 2, 3, 1, 3, 1000, 1000, 9))
  )

  private val PopulationVarianceData = List(
    (25.0,          dList(10, 20)),
    (25.0,          dList(10.5, 20.5)),
    (1493.2,        dList(1, 10, 3, 1, 100)),
    (0.8,           dList(1, 2, 3, 1, 3)),
    (186319.984375, dList(1, 2, 3, 1, 3, 1000, 1000, 9))
  )

  private val PopulationStddevData = List(
    (5,                  dList(10, 20)),
    (5.0,                dList(10.5, 20.5)),
    (38.64194612076364,  dList(1, 10, 3, 1, 100)),
    (0.8944271909999159, dList(1, 2, 3, 1, 3)),
    (431.64798664536823, dList(1, 2, 3, 1, 3, 1000, 1000, 9))
  )

  "sample standard deviation" should "produce proper values" in {
    for ((expected, values) <- SampleStddevData) {
      sampleStandardDeviation(values: _*) shouldBe expected
    }
  }

  it should "be the square root of the sample variance" in {
    for ((expected, values) <- SampleStddevData) {
      val variance = sampleVariance(values: _*)
      val stddev = sampleStandardDeviation(values: _*)

      sqrt(variance) shouldBe stddev
    }
  }

  "population variance" should "produce proper values" in {
    for ((expected, values) <- PopulationVarianceData) {
      populationVariance(values: _*) shouldBe expected
    }
  }

  "population standard deviation" should "produce proper values" in {
    for ((expected, values) <- PopulationStddevData) {
      populationStandardDeviation(values: _*) shouldBe expected
    }
  }

  it should "be the square root of the population variance" in {
    for ((expected, values) <- PopulationStddevData) {
      val variance = populationVariance(values: _*)
      val stddev = populationStandardDeviation(values: _*)

      sqrt(variance) shouldBe stddev
    }
  }

  "range" should "produce proper values" in {
    // Must be all the same type
    val Data1 = List[(Double, List[Double])](
      (29.0,  dList(1 to 30: _*)),
      (100.0, dList(1, 100, 30, 28.8, 101)),
      (999.0, dList(1 to 1000: _*))
    )
    val Data2 = List[(Int, List[Int])](
      (29,  (1 to 30).toList),
      (100, List(1, 100, 30, 28, 101)),
      (999, (1 to 1000).toList)
    )

    for ((expected, values) <- Data1) {
      range(values: _*) shouldBe expected
    }

    for ((expected, values) <- Data2) {
      range(values: _*) shouldBe expected
    }
  }

  it should "work with a single value" in {
    val Data = List[(Int, Int)](
      (0,   1),
      (0, 100)
    )

    for ((expected, value) <- Data) {
      range(value) shouldBe expected
    }
  }
}
