#define AUDIO_BUFFER_SIZE 16384


class AudioBuffer {
    public:
        uint8_t *enqueuePtr();
        uint8_t deque();
        bool isQueueFull();
        bool hasNext();
};