package domala.jdbc.query

import java.util.Collections

import domala.internal.expr.ExpressionEvaluator
import domala.internal.jdbc.sql.NodePreparedSqlBuilder
import org.seasar.doma.internal.expr.Value
import org.seasar.doma.internal.jdbc.sql.{SqlContext, SqlParser}
import org.seasar.doma.internal.jdbc.sql.node.{ExpandNode, PopulateNode}
import org.seasar.doma.internal.util.AssertionUtil
import org.seasar.doma.jdbc._
import org.seasar.doma.jdbc.entity.EntityType
import org.seasar.doma.jdbc.query.{AbstractQuery, BatchModifyQuery}

abstract class SqlAnnotationBatchModifyQuery[ELEMENT](protected val elementClass: Class[ELEMENT], protected val kind: SqlKind, sqlString: String) extends AbstractQuery with BatchModifyQuery {

  protected var EMPTY_STRINGS = new Array[String](0)
  protected var parameterName: String = _
  protected val sqlNode: SqlNode = new SqlParser(sqlString).parse()
  protected var optimisticLockCheckRequired: Boolean = false
  private var executable = false
  private var sqlExecutionSkipCause = SqlExecutionSkipCause.BATCH_TARGET_NONEXISTENT
  protected var batchSize: Int = 0
  protected var sqlLogType: SqlLogType = _
  protected var includedPropertyNames: Array[String] = EMPTY_STRINGS
  protected var excludedPropertyNames: Array[String] = EMPTY_STRINGS
  protected var elements: java.util.List[ELEMENT] = _
  protected var currentEntity: ELEMENT = _
  protected var sqls: java.util.List[PreparedSql] = _

  AssertionUtil.assertNotNull(kind, "")

  override def prepare(): Unit = {
    super.prepare()
    AssertionUtil.assertNotNull(method, parameterName, elements, sqls, "", "")
  }

  protected def prepareOptions(): Unit = {
    if (this.queryTimeout <= 0) this.queryTimeout = this.config.getQueryTimeout
    if (this.batchSize <= 0) this.batchSize = this.config.getBatchSize
  }

  protected def prepareSql(): Unit = {
    val value = new Value(elementClass, currentEntity)
    val evaluator = new ExpressionEvaluator(Collections.singletonMap(parameterName, value), this.config.getDialect.getExpressionFunctions, this.config.getClassHelper)
    val sqlBuilder = new NodePreparedSqlBuilder(this.config, this.kind, evaluator, this.sqlLogType, this.expandColumns _, this.populateValues _)
    val sql = sqlBuilder.build(this.sqlNode, this.comment _)
    sqls.add(sql)
  }

  protected def expandColumns(node: ExpandNode): java.util.List[String] = throw new UnsupportedOperationException

  protected def populateValues(node: PopulateNode, context: SqlContext): Unit = throw new UnsupportedOperationException

  def setParameterName(parameterName: String): Unit = {
    this.parameterName = parameterName
  }

  import org.seasar.doma.jdbc.PreparedSql
  import java.util

  def setElements(elements: Iterable[ELEMENT]): Unit = {
    AssertionUtil.assertNotNull(elements, "")
    if (elements.isInstanceOf[util.Collection[_]]) this.elements = new java.util.ArrayList[ELEMENT](elements.asInstanceOf[java.util.Collection[ELEMENT]])
    else {
      this.elements = new java.util.ArrayList[ELEMENT]
      for (element <- elements) {
        this.elements.add(element)
      }
    }
    this.sqls = new java.util.ArrayList[PreparedSql](this.elements.size)
  }

  def getEntities: java.util.List[ELEMENT] = elements

  def setBatchSize(batchSize: Int): Unit = {
    this.batchSize = batchSize
  }

  def setSqlLogType(sqlLogType: SqlLogType): Unit = {
    this.sqlLogType = sqlLogType
  }

  def setIncludedPropertyNames(includedPropertyNames: String*): Unit = {
    this.includedPropertyNames = includedPropertyNames.toArray
  }

  def setExcludedPropertyNames(excludedPropertyNames: String*): Unit = {
    this.excludedPropertyNames = excludedPropertyNames.toArray
  }

  override def getSql: PreparedSql = this.sqls.get(0)

  override def getSqls: java.util.List[PreparedSql] = this.sqls

  override def getConfig: Config = this.config

  override def isOptimisticLockCheckRequired: Boolean = this.optimisticLockCheckRequired

  override def isAutoGeneratedKeysSupported: Boolean = false

  override def isExecutable: Boolean = executable

  override def getSqlExecutionSkipCause: SqlExecutionSkipCause = this.sqlExecutionSkipCause

  override def getBatchSize: Int = batchSize

  override def getSqlLogType: SqlLogType = this.sqlLogType

  override def toString: String = sqls.toString

  protected def setExecutable(): Unit = {
    this.executable = true
    this.sqlExecutionSkipCause = null
  }
}
