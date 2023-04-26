#ifndef AUDIO_BUFFER_H
#define AUDIO_BUFFER_H

#include "logger.cpp"

#define AUDIO_BUFFER_SIZE 2048 //16384 
#define AUDIO_BUFFER_ENQUEUE_AMT 177

class AudioBuffer{
  private:
    int front = 0;
    int rear = -1;
    int size = 0;
    uint8_t array[AUDIO_BUFFER_SIZE];

    bool isArrayFull(){
      return (rear + AUDIO_BUFFER_ENQUEUE_AMT) <= AUDIO_BUFFER_SIZE;
    }

    int incIndex(int index){
      return (index +1) % AUDIO_BUFFER_SIZE;
    }

  public:
    uint8_t *enqueuePtr(){
      if(isQueueFull()){
        myLog("ERR: queue is full!");
      }
      rear = isArrayFull() ? 0 : incIndex(rear);
      size += AUDIO_BUFFER_ENQUEUE_AMT;
      return &array[rear];
    }

    uint8_t deque(){
      if(size == 0){
        myLog("ERR: Dequeue on empty queue!");
        return -1;
      }
      uint8_t item = array[front];
      front = incIndex(front);
      size--;
      return item;
    }

    bool isQueueFull(){
      return size + AUDIO_BUFFER_ENQUEUE_AMT >= AUDIO_BUFFER_SIZE;
    }

    bool hasNext(){
      return size != 0;
    }
};


#endif