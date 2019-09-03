package fr.iscpif

import java.io._
import org.tukaani.xz._

package object xz {

  implicit class XZFile(file: File) {

    def extractXZ(to: File) = {
      if (!file.getName.endsWith(".xz")) throw new java.io.IOException(s"$file is not a XZ file")
      else {
        val inputStream = new FileInputStream(file)
        val outputStream = new FileOutputStream(to)
        val inxz = extractToStream

        val buffer = new Array[Byte](inputStream.available)
        Iterator.continually(inxz.read(buffer)).takeWhile(_ != -1).foreach {
          outputStream.write(buffer, 0, _)
        }

        inxz.close
      }
    }

    def extractToStream: InputStream = {
      if (!file.getName.endsWith(".xz")) throw new java.io.IOException(s"$file is not a XZ file")
      else {
        val inputStream = new FileInputStream(file)
        new XZInputStream(inputStream, 100 * 1024)
      }
    }

  def compressXZ(to: File) = {

    val outfile = new FileOutputStream(to)
    val outxz = new XZOutputStream(outfile, new LZMA2Options(8), XZ.CHECK_SHA256)

    val infile = new FileInputStream(file)
    val buffer = new Array[Byte](8192)

    Iterator.continually(infile.read(buffer)).takeWhile(_ != -1).foreach { size =>
      outxz.write(buffer, 0, size)
    }

    outxz.finish
  }
}

//  def compress(files: Seq[File], to: File) = {
//
//
//    val outfile = new FileOutputStream(to)
//    val outxz = new XZOutputStream(outfile, new LZMA2Options(8), XZ.CHECK_SHA256)
//
//    for {
//      f <- files
//    } yield {
//      println("ff " + f.getAbsolutePath)
//      val infile = new FileInputStream(f)
//      val buffer = new Array[Byte](8192)
//
//      Iterator.continually(infile.read(buffer)).takeWhile(_ != -1).foreach { size =>
//        // inxz.read(buffer)
//        println("SIze " + size)
//        println("write " + buffer.toString)
//        outxz.write(buffer, 0, size)
//      }
//    }
//    outxz.finish
//  }

}