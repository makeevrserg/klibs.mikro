package ru.astrainteractive.klibs.mikro.core.domain

/**
 * UseCase from CleanArchitecture
 */
interface UseCase<in Input, out Output> {
    /**
     * [Suspended] is use to call suspended methods
     */
    interface Suspended<in Input, out Output> : UseCase<Input, Output> {
        suspend operator fun invoke(input: Input): Output

        interface Simple<out Output> : Suspended<Unit, Output> {
            suspend operator fun invoke(): Output = invoke(Unit)
        }
    }

    /**
     * [Blocking] is used to call blocking methods without coroutines
     */
    interface Blocking<in Input, out Output> : UseCase<Input, Output> {
        operator fun invoke(input: Input): Output

        interface Simple<out Output> : Blocking<Unit, Output> {
            operator fun invoke(): Output = invoke(Unit)
        }
    }
}
