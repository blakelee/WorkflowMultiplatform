package net.blakelee.workflowmultiplatform

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform