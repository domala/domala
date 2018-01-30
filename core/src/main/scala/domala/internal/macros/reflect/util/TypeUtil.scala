package domala.internal.macros.reflect.util

import java.util.function.Supplier

import domala.jdbc.`type`.Types
import domala.wrapper.Wrapper

import scala.reflect.macros.blackbox

object TypeUtil {

  def generateWrapperSupplier[C <: blackbox.Context](c: C)(tpe: c.universe.Type): c.Expr[Supplier[_ <: Wrapper[_]]] = {
    import c.universe._
    MacroTypeConverter.of(c).toType(tpe) match {
      case Types.BigDecimalType => reify { Types.BigDecimalType.wrapperSupplier }
      case Types.JavaBigDecimalType => reify { Types.JavaBigDecimalType.wrapperSupplier }
      case Types.BigIntType => reify { Types.BigIntType.wrapperSupplier }
      case Types.BigIntegerType => reify { Types.BigIntegerType.wrapperSupplier }
      case Types.IntType => reify { Types.IntType.wrapperSupplier }
      case Types.AnyType => reify { Types.AnyType.wrapperSupplier }
      case Types.BytesType => reify { Types.BytesType.wrapperSupplier }
      case Types.LongType => reify { Types.LongType.wrapperSupplier }
      case Types.DoubleType => reify { Types.DoubleType.wrapperSupplier }
      case Types.BooleanType => reify { Types.BooleanType.wrapperSupplier }
      case Types.ByteType => reify { Types.ByteType.wrapperSupplier }
      case Types.ShortType => reify { Types.ShortType.wrapperSupplier }
      case Types.FloatType => reify { Types.FloatType.wrapperSupplier }
      case Types.StringType => reify { Types.StringType.wrapperSupplier }
      case Types.LocalDateType => reify { Types.LocalDateType.wrapperSupplier }
      case Types.LocalTimeType => reify { Types.LocalTimeType.wrapperSupplier }
      case Types.LocalDateTimeType => reify { Types.LocalDateTimeType.wrapperSupplier }
      case Types.DateType => reify { Types.DateType.wrapperSupplier }
      case Types.UtilDateType => reify { Types.UtilDateType.wrapperSupplier }
      case Types.TimeType => reify { Types.TimeType.wrapperSupplier }
      case Types.TimestampType => reify { Types.TimestampType.wrapperSupplier }
      case Types.BlobType => reify { Types.BlobType.wrapperSupplier }
      case Types.ClobType => reify { Types.ClobType.wrapperSupplier }
      case Types.NClobType => reify { Types.NClobType.wrapperSupplier }
      case Types.SQLXMLType => reify { Types.SQLXMLType.wrapperSupplier }
      case _ => c.abort(c.enclosingPosition, "error")
    }
  }

}
