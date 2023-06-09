package com.squareup.workflow1.config

import com.squareup.workflow1.RuntimeConfig
import com.squareup.workflow1.RuntimeConfigOptions
import com.squareup.workflow1.RuntimeConfigOptions.CONFLATE_STALE_RENDERINGS
import com.squareup.workflow1.RuntimeConfigOptions.RENDER_ONLY_WHEN_STATE_CHANGES
import com.squareup.workflow1.WorkflowExperimentalRuntime

public class JvmTestRuntimeConfigTools {
  public companion object {
    /**
     * Helper for Configuration for the workflow runtime in an application.
     * This allows one to specify a project property from the gradle build to choose a runtime.
     * e.g. add "-Pworkflow.runtime=conflate" in your gradle build to build the conflate runtime
     * into the application.
     *
     * The [WorkflowTestRuntime] already calls this utility, but if starting your own runtime, then
     * call this function and pass the result to the call to [renderWorkflowIn] as the
     * [RuntimeConfig].
     *
     * Current options are:
     * "conflate" : Process all queued actions before passing rendering
     *      to the UI layer.
     * "baseline" : Original Workflow Runtime. Note that this doesn't need to
     *      be specified as it is the current default and is assumed by this utility.
     *
     * Then, these can be combined (via '-') with:
     * "stateChange" : Only re-render when the state of some WorkflowNode has been changed by an
     *   action cascade.
     *
     * E.g., "baseline-stateChange" to turn on the stateChange option with the baseline runtime.
     *
     */
    @OptIn(WorkflowExperimentalRuntime::class)
    public fun getTestRuntimeConfig(): RuntimeConfig {
      return when
      (val runtimeConfig = System.getProperty("workflow.runtime", "baseline")) {
        "conflate" -> setOf(CONFLATE_STALE_RENDERINGS)
        "conflate-stateChange" -> setOf(CONFLATE_STALE_RENDERINGS, RENDER_ONLY_WHEN_STATE_CHANGES)
        "baseline-stateChange" -> setOf(RENDER_ONLY_WHEN_STATE_CHANGES)
        "", "baseline" -> RuntimeConfigOptions.RENDER_PER_ACTION
        else ->
          throw IllegalArgumentException("Unrecognized config \"$runtimeConfig\"")
      }
    }
  }
}
