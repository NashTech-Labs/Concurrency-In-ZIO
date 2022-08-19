package Concurrency

import zio.{ExitCode, UIO, URIO, ZIO}

object SynchronizingFiber extends zio.App {

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = joiningAsynchronousFibers.exitCode

  val task1: UIO[String] = ZIO.succeed("Task-1")
  val task2: UIO[String] = ZIO.succeed("Task-2")
  val task3: UIO[String] = ZIO.succeed("Task-3")

  def printThread = s"[${Thread.currentThread().getName}]"

  def joiningAsynchronousFibers() = for {
    task1 <- task1.debug(printThread).fork
    task2 <- task2.debug(printThread).fork
    task3 = task1.zip(task2)  // zip method combines two fibers into a single fiber that produces both results.
    finalTask <- task3.join.debug(printThread)
    _ <- ZIO.succeed(s"$finalTask is done").debug(printThread)
  } yield ()

  /***
   * Output:
   * [zio-default-async-3]: Task-2
   * [zio-default-async-2]: Task-1
   * [zio-default-async-6]: (Task-1,Task-2)
   * [zio-default-async-6]: (Task-1,Task-2) is done
   ------------------------------------------------
   * Task1 and Task2 are working asynchronously whereas Task3 is waiting for them to complete,
   * So that it can zip async-3(task2) and async-2(task1) together into a single fiber async-6(task1, task2),
   * Then in the finalTask - task3 in joined and printed the result.
   * Join - {Joins the fiber, which suspends the joining fiber until the result of the fiber has been determined,
   * we can wait for the result of concurrent computation and eventually use it}
   */
}
