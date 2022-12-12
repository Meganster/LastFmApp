package com.appsfactory.testtask

import org.mockito.Mockito

/**
 * val mock: Observer<Int> = mockAny()
 * val list: List<String> = listOf(mockAny())
 * */
inline fun <reified T> mockAny(): T = Mockito.mock(T::class.java, Mockito.RETURNS_DEEP_STUBS)

/**
 * Mock any method matcher
 *
 * Example:
 * someMethod(param: String)
 * someMethod(anySafe(String::class.kotlin))
 * */
fun <T> anySafe(type: Class<T>): T = Mockito.any<T>(type)

inline fun <reified T> anyArg(): T = Mockito.any<T>(T::class.java)

inline fun <reified T> nullableArg(): T = Mockito.nullable<T>(T::class.java)

/**
 * Handler for methodCall
 * */
fun <T> mockWhen(methodCall: T, thenReturn: () -> T) = Mockito.`when`(methodCall).thenReturn(thenReturn())