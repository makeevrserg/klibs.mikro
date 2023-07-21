package ru.astrainteractive.klibs.mikro.core.domain

/**
 * UseCase from CleanArchitecture
 */
interface UseCase<out Output> {
    interface Parametrized<in Input, out Output> : UseCase<Output> {
        suspend fun invoke(input: Input): Output
    }

    interface Simple<out Output> : UseCase<Output> {
        suspend fun invoke(): Output
    }
}
