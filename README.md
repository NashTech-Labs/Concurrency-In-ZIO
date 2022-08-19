# About this Tech hub

In this Techhub we will going to cover *Concurrency in Zio*.

Way to achieve concurrency both synchronously and Asynchronously using *Fiber* model.

# Concurrency in ZIO

ZIO is a highly concurrent framework, powered by fibers, which are lightweight virtual threads that achieve massive scalability compared to threads, augmented with resource-safe cancellation, which powers many features in ZIO.

This powerful concurrency model lets you do more with less, achieving highly-scalable, ultra low-latency applications that are globally efficient and resource-safe.

## Fibers

Fibers are lightweight equivalents of operating system threads. Like a thread, a
fiber models a running computation and instructions on a single fiber are executed
sequentially. However, fibers are much less costly to create than operating system
threads, so we can have hundreds of thousands of fibers in our program at any
given time whereas maintaining this number of threads would have a severe
performance impact. Unlike threads, fibers are also safely interruptible and can
be joined without blocking.

## Methods

- `fork`
- `zip`
- `join`
