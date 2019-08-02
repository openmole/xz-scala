package org.tukaani.xz

import java.io._


object ExtractXZDemo extends App {

  val xzfile = new File(args(0))
  val output = new File(args(1))

  xzfile.extractXZ(output)


}

object CompressXZDemo extends App {

  val input = new File(args(0))
  val output = new File(args(1))

  input.compressXZ(output)
}