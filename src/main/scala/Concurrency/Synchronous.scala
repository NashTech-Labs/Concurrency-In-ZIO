package Concurrency

import zio.{ExitCode, UIO, URIO, ZIO}

object Synchronous extends zio.App {

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = synchronousRoutine().exitCode

  val task1: UIO[String] = ZIO.succeed("Task-1")
  val task2: UIO[String] = ZIO.succeed("Task-2")
  val task3: UIO[String] = ZIO.succeed("Task-3")

  def printThread = s"[${Thread.currentThread().getName}]"

  def synchronousRoutine() = for {
    _ <- task1.debug(printThread)
    _ <- task2.debug(printThread)
    _ <- task3.debug(printThread)
  } yield ()

  /**
   * Output:
   * [zio-default-async-1]: Task-1
   * [zio-default-async-1]: Task-2
   * [zio-default-async-1]: Task-3
   */
}
