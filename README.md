# xz-scala
Scala facade of the tukaani.org/xz-java library.

## Compress a File
```scala
import org.tukaani.xz._

val inputFile = new File(path/to/xzFile)
val xzFile = new File(path/to/output)

inputFile.compressXZ(xzFile)
```

## Extract a XZ file

```scala
import org.tukaani.xz._

val xzfile = new File(args(0))
val outputFile = new File(args(1))

xzfile.extractXZ(outputFile)
```
