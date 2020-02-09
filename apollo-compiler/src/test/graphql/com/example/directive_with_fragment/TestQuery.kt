// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.directive_with_fragment

import com.apollographql.apollo.api.InputFieldMarshaller
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.OperationName
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.ResponseFieldMapper
import com.apollographql.apollo.api.ResponseFieldMarshaller
import com.apollographql.apollo.api.ResponseReader
import com.apollographql.apollo.api.internal.SimpleOperationResponseParser
import com.apollographql.apollo.internal.QueryDocumentMinifier
import com.apollographql.apollo.response.ScalarTypeAdapters
import com.apollographql.apollo.response.ScalarTypeAdapters.DEFAULT
import com.example.directive_with_fragment.fragment.HeroDetails
import com.example.directive_with_fragment.fragment.HumanDetails
import com.example.directive_with_fragment.type.CustomType
import java.io.IOException
import kotlin.Any
import kotlin.Array
import kotlin.Boolean
import kotlin.String
import kotlin.Suppress
import kotlin.collections.Map
import kotlin.jvm.Throws
import kotlin.jvm.Transient
import okio.BufferedSource

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter")
data class TestQuery(
  val withDetails: Boolean,
  val skipHumanDetails: Boolean
) : Query<TestQuery.Data, TestQuery.Data, Operation.Variables> {
  @Transient
  private val variables: Operation.Variables = object : Operation.Variables() {
    override fun valueMap(): Map<String, Any?> = mutableMapOf<String, Any?>().apply {
      this["withDetails"] = this@TestQuery.withDetails
      this["skipHumanDetails"] = this@TestQuery.skipHumanDetails
    }

    override fun marshaller(): InputFieldMarshaller = InputFieldMarshaller { writer ->
      writer.writeBoolean("withDetails", this@TestQuery.withDetails)
      writer.writeBoolean("skipHumanDetails", this@TestQuery.skipHumanDetails)
    }
  }

  override fun operationId(): String = OPERATION_ID
  override fun queryDocument(): String = QUERY_DOCUMENT
  override fun wrapData(data: Data?): Data? = data
  override fun variables(): Operation.Variables = variables
  override fun name(): OperationName = OPERATION_NAME
  override fun responseFieldMapper(): ResponseFieldMapper<Data> = ResponseFieldMapper {
    Data(it)
  }

  @Throws(IOException::class)
  override fun parse(source: BufferedSource, scalarTypeAdapters: ScalarTypeAdapters): Response<Data>
      = SimpleOperationResponseParser.parse(source, this, scalarTypeAdapters)

  @Throws(IOException::class)
  override fun parse(source: BufferedSource): Response<Data> = parse(source, DEFAULT)

  data class Hero(
    val __typename: String = "Character",
    /**
     * The ID of the character
     */
    val id: String,
    val fragments: Fragments
  ) {
    fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller { writer ->
      writer.writeString(RESPONSE_FIELDS[0], this@Hero.__typename)
      writer.writeCustom(RESPONSE_FIELDS[1] as ResponseField.CustomTypeField, this@Hero.id)
      this@Hero.fragments.marshaller().marshal(writer)
    }

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.forString("__typename", "__typename", null, false, null),
          ResponseField.forCustomType("id", "id", null, false, CustomType.ID, null)
          )

      operator fun invoke(reader: ResponseReader): Hero = reader.run {
        val __typename = readString(RESPONSE_FIELDS[0])
        val id = readCustomType<String>(RESPONSE_FIELDS[1] as ResponseField.CustomTypeField)
        val fragments = Fragments(reader)
        Hero(
          __typename = __typename,
          id = id,
          fragments = fragments
        )
      }
    }

    data class Fragments(
      val heroDetails: HeroDetails?,
      val humanDetails: HumanDetails?
    ) {
      fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller { writer ->
        writer.writeFragment(this@Fragments.heroDetails?.marshaller())
        writer.writeFragment(this@Fragments.humanDetails?.marshaller())
      }

      companion object {
        private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
            ResponseField.forFragment("__typename", "__typename", listOf(
              ResponseField.Condition.booleanCondition("withDetails", false),
              ResponseField.Condition.booleanCondition("skipHumanDetails", true),
              ResponseField.Condition.typeCondition(arrayOf("Human", "Droid"))
            )),
            ResponseField.forFragment("__typename", "__typename", listOf(
              ResponseField.Condition.booleanCondition("withDetails", false),
              ResponseField.Condition.typeCondition(arrayOf("Human"))
            ))
            )

        operator fun invoke(reader: ResponseReader): Fragments = reader.run {
          val heroDetails = readFragment<HeroDetails>(RESPONSE_FIELDS[0]) { reader ->
            HeroDetails(reader)
          }
          val humanDetails = readFragment<HumanDetails>(RESPONSE_FIELDS[1]) { reader ->
            HumanDetails(reader)
          }
          Fragments(
            heroDetails = heroDetails,
            humanDetails = humanDetails
          )
        }
      }
    }
  }

  data class Data(
    val hero: Hero?
  ) : Operation.Data {
    override fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller { writer ->
      writer.writeObject(RESPONSE_FIELDS[0], this@Data.hero?.marshaller())
    }

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.forObject("hero", "hero", null, true, null)
          )

      operator fun invoke(reader: ResponseReader): Data = reader.run {
        val hero = readObject<Hero>(RESPONSE_FIELDS[0]) { reader ->
          Hero(reader)
        }
        Data(
          hero = hero
        )
      }
    }
  }

  companion object {
    const val OPERATION_ID: String =
        "e7ae0709b15d61fbba95a5c2e74b439fbed8ccf8d68fd389f4dd8250b55efeaf"

    val QUERY_DOCUMENT: String = QueryDocumentMinifier.minify(
          """
          |query TestQuery(${'$'}withDetails: Boolean!, ${'$'}skipHumanDetails: Boolean!) {
          |  hero {
          |    __typename
          |    id
          |    ... HeroDetails @include(if: ${'$'}withDetails) @skip(if: ${'$'}skipHumanDetails)
          |    ... HumanDetails @include(if: ${'$'}withDetails)
          |  }
          |}
          |fragment HeroDetails on Character {
          |  __typename
          |  name
          |}
          |fragment HumanDetails on Human {
          |  __typename
          |  homePlanet
          |}
          """.trimMargin()
        )

    val OPERATION_NAME: OperationName = OperationName { "TestQuery" }
  }
}