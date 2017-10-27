package domala.internal.macros

import org.scalatest.FunSuite
import scala.meta._

class EntityTypeGeneratorTestSuite extends FunSuite {
  test("normal entity") {
    val cls = q"""
case class Person(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  id: Option[ID[Person]] = None,
  @Column(updatable = false)
  name: Option[Name],
  age: Option[Int],
  address: Address,
  departmentId: Option[Int],
  @Version
  version: Option[Int] = Option(-1)
)
"""
    val expect = """object Person extends org.seasar.doma.jdbc.entity.AbstractEntityType[Person] {
  object ListenerHolder {
    domala.internal.macros.reflect.EntityReflectionMacros.validateListener(classOf[Person], classOf[org.seasar.doma.jdbc.entity.NullEntityListener[Person]])
    val listener = new org.seasar.doma.jdbc.entity.NullEntityListener[Person]()
  }
  def apply(id: Option[ID[Person]] = None, name: Option[Name], age: Option[Int], address: Address, departmentId: Option[Int], version: Option[Int] = Option(-1)): Person = new Person(id, name, age, address, departmentId, version)
  def unapply(x: Person): Option[(Option[ID[Person]], Option[Name], Option[Int], Address, Option[Int], Option[Int])] = Some((x.id, x.name, x.age, x.address, x.departmentId, x.version))
  private val __namingType: org.seasar.doma.jdbc.entity.NamingType = null
  private val __idGenerator = new org.seasar.doma.jdbc.id.BuiltinIdentityIdGenerator()
  val __idList = new java.util.ArrayList[org.seasar.doma.jdbc.entity.EntityPropertyType[Person, _]]()
  val __list = new java.util.ArrayList[org.seasar.doma.jdbc.entity.EntityPropertyType[Person, _]](6)
  val __map = new java.util.HashMap[String, org.seasar.doma.jdbc.entity.EntityPropertyType[Person, _]](6)
  val __collections = domala.internal.macros.reflect.EntityCollections[Person](__list, __map, __idList)
  val $id = domala.internal.macros.reflect.EntityReflectionMacros.generatePropertyType[Option[ID[Person]], Person, ID[Person]](classOf[Person], "id", __namingType, true, true, __idGenerator, false, false, null, "", true, true, false, __collections)
  val $name = domala.internal.macros.reflect.EntityReflectionMacros.generatePropertyType[Option[Name], Person, Name](classOf[Person], "name", __namingType, false, false, __idGenerator, false, false, null, "", true, false, false, __collections)
  val $age = domala.internal.macros.reflect.EntityReflectionMacros.generatePropertyType[Option[Int], Person, Integer](classOf[Person], "age", __namingType, false, false, __idGenerator, false, true, () => new org.seasar.doma.wrapper.IntegerWrapper(): org.seasar.doma.wrapper.Wrapper[Integer], "", true, true, false, __collections)
  val $address = domala.internal.macros.reflect.EntityReflectionMacros.generatePropertyType[Address, Person, Address](classOf[Person], "address", __namingType, false, false, __idGenerator, false, false, null, "", true, true, false, __collections)
  val $departmentId = domala.internal.macros.reflect.EntityReflectionMacros.generatePropertyType[Option[Int], Person, Integer](classOf[Person], "departmentId", __namingType, false, false, __idGenerator, false, true, () => new org.seasar.doma.wrapper.IntegerWrapper(): org.seasar.doma.wrapper.Wrapper[Integer], "", true, true, false, __collections)
  val $version = domala.internal.macros.reflect.EntityReflectionMacros.generatePropertyType[Option[Int], Person, Integer](classOf[Person], "version", __namingType, false, false, __idGenerator, true, true, () => new org.seasar.doma.wrapper.IntegerWrapper(): org.seasar.doma.wrapper.Wrapper[Integer], "", true, true, false, __collections)
  val __listenerSupplier: java.util.function.Supplier[org.seasar.doma.jdbc.entity.NullEntityListener[Person]] = () => ListenerHolder.listener
  val __immutable = true
  val __name = "Person"
  val __catalogName = ""
  val __schemaName = ""
  val __tableName = ""
  val __isQuoteRequired = false
  val __idPropertyTypes = java.util.Collections.unmodifiableList(__idList)
  val __entityPropertyTypes = java.util.Collections.unmodifiableList(__list)
  val __entityPropertyTypeMap: java.util.Map[String, org.seasar.doma.jdbc.entity.EntityPropertyType[Person, _]] = java.util.Collections.unmodifiableMap(__map)
  override def getNamingType: org.seasar.doma.jdbc.entity.NamingType = __namingType
  override def isImmutable: Boolean = __immutable
  override def getName: String = __name
  override def getCatalogName: String = __catalogName
  override def getSchemaName: String = __schemaName
  override def getTableName: String = getTableName(org.seasar.doma.jdbc.Naming.DEFAULT.apply _)
  override def getTableName(namingFunction: java.util.function.BiFunction[org.seasar.doma.jdbc.entity.NamingType, String, String]): String = {
    if (__tableName.isEmpty) {
      namingFunction.apply(__namingType, __name)
    } else {
      __tableName
    }
  }
  override def isQuoteRequired = __isQuoteRequired
  override def preInsert(entity: Person, context: org.seasar.doma.jdbc.entity.PreInsertContext[Person]) = {
    val __listenerClass = classOf[org.seasar.doma.jdbc.entity.NullEntityListener[Person]]
    val __listener = context.getConfig.getEntityListenerProvider.get[Person, org.seasar.doma.jdbc.entity.NullEntityListener[Person]](__listenerClass, __listenerSupplier)
    __listener.preInsert(entity, context)
  }
  override def preUpdate(entity: Person, context: org.seasar.doma.jdbc.entity.PreUpdateContext[Person]) = {
    val __listenerClass = classOf[org.seasar.doma.jdbc.entity.NullEntityListener[Person]]
    val __listener = context.getConfig.getEntityListenerProvider.get[Person, org.seasar.doma.jdbc.entity.NullEntityListener[Person]](__listenerClass, __listenerSupplier)
    __listener.preUpdate(entity, context)
  }
  override def preDelete(entity: Person, context: org.seasar.doma.jdbc.entity.PreDeleteContext[Person]) = {
    val __listenerClass = classOf[org.seasar.doma.jdbc.entity.NullEntityListener[Person]]
    val __listener = context.getConfig.getEntityListenerProvider.get[Person, org.seasar.doma.jdbc.entity.NullEntityListener[Person]](__listenerClass, __listenerSupplier)
    __listener.preDelete(entity, context)
  }
  override def postInsert(entity: Person, context: org.seasar.doma.jdbc.entity.PostInsertContext[Person]) = {
    val __listenerClass = classOf[org.seasar.doma.jdbc.entity.NullEntityListener[Person]]
    val __listener = context.getConfig.getEntityListenerProvider.get[Person, org.seasar.doma.jdbc.entity.NullEntityListener[Person]](__listenerClass, __listenerSupplier)
    __listener.postInsert(entity, context)
  }
  override def postUpdate(entity: Person, context: org.seasar.doma.jdbc.entity.PostUpdateContext[Person]) = {
    val __listenerClass = classOf[org.seasar.doma.jdbc.entity.NullEntityListener[Person]]
    val __listener = context.getConfig.getEntityListenerProvider.get[Person, org.seasar.doma.jdbc.entity.NullEntityListener[Person]](__listenerClass, __listenerSupplier)
    __listener.postUpdate(entity, context)
  }
  override def postDelete(entity: Person, context: org.seasar.doma.jdbc.entity.PostDeleteContext[Person]) = {
    val __listenerClass = classOf[org.seasar.doma.jdbc.entity.NullEntityListener[Person]]
    val __listener = context.getConfig.getEntityListenerProvider.get[Person, org.seasar.doma.jdbc.entity.NullEntityListener[Person]](__listenerClass, __listenerSupplier)
    __listener.postDelete(entity, context)
  }
  override def getEntityPropertyTypes = __entityPropertyTypes
  override def getEntityPropertyType(__name: String) = __entityPropertyTypeMap.get(__name)
  override def getIdPropertyTypes = __idPropertyTypes
  override def getGeneratedIdPropertyType: org.seasar.doma.jdbc.entity.GeneratedIdPropertyType[_ >: Person, Person, _, _] = $id.asInstanceOf[org.seasar.doma.jdbc.entity.GeneratedIdPropertyType[Person, Person, _ <: Number, _]]
  override def getVersionPropertyType: org.seasar.doma.jdbc.entity.VersionPropertyType[_ >: Person, Person, _, _] = $version.asInstanceOf[org.seasar.doma.jdbc.entity.VersionPropertyType[Person, Person, _ <: Number, _]]
  override def getTenantIdPropertyType: org.seasar.doma.jdbc.entity.TenantIdPropertyType[_ >: Person, Person, _, _] = null
  override def newEntity(__args: java.util.Map[String, org.seasar.doma.jdbc.entity.Property[Person, _]]) = new Person(domala.internal.macros.reflect.EntityReflectionMacros.readProperty[Option[ID[Person]], Person](classOf[Person], __args, "id"), domala.internal.macros.reflect.EntityReflectionMacros.readProperty[Option[Name], Person](classOf[Person], __args, "name"), domala.internal.macros.reflect.EntityReflectionMacros.readProperty[Option[Int], Person](classOf[Person], __args, "age"), domala.internal.macros.reflect.EntityReflectionMacros.readProperty[Address, Person](classOf[Person], __args, "address"), domala.internal.macros.reflect.EntityReflectionMacros.readProperty[Option[Int], Person](classOf[Person], __args, "departmentId"), domala.internal.macros.reflect.EntityReflectionMacros.readProperty[Option[Int], Person](classOf[Person], __args, "version"))
  override def getEntityClass = classOf[Person]
  override def getOriginalStates(__entity: Person): Person = null
  override def saveCurrentStates(__entity: Person): Unit = {}
}"""
    val ret = EntityTypeGenerator.generate(cls, Nil)
    assert(ret.syntax == expect)
  }

}
