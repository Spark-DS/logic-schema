package sparkDS.logicSchema.dataSpec

/**
 * Thrown when a spec fails to analyze.
 */
class AnalysisException protected[dataSpec](
                                             val message: String,
                                             val cause: Option[Throwable] = None)
  extends Exception(message, cause.orNull) with Serializable
