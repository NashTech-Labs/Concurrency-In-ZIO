package Concurrency

import zio.{ExitCode, UIO, URIO, ZIO}

object Asynchronous extends zio.App {

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = aSynchronousRoutine().exitCode

  val task1: UIO[String] = ZIO.succeed("Task-1")
  val task2: UIO[String] = ZIO.succeed("Task-2")
  val task3: UIO[String] = ZIO.succeed("Task-3")

  def printThread = s"[${Thread.currentThread().getName}]"

  def aSynchronousRoutine() = for {
    _ <- task1.debug(printThread).fork
    _ <- task2.debug(printThread).fork
    _ <- task3.debug(printThread).fork
  } yield ()

  /**
   * Output:
   * [zio-default-async-3]: Task-2
   * [zio-default-async-4]: Task-3
   * [zio-default-async-2]: Task-1
   * If you run this code, The output might not be excatly same as it is working asynchronously
   */

}
