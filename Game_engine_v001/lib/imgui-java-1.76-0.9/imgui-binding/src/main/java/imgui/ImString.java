package imgui;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * Wrapper for {@link String} to use inside of th Dear ImGui input widgets.
 */
public final class ImString {
    /**
     * Default size of the inner buffer, if {@link ImString} created with a constructor without args.
     */
    public static final short DEFAULT_LENGTH = 100;
    /**
     * Size of ImGui caret which is shown during the input text focus.
     */
    public static final short CARET_LEN = 1;

    /**
     * Configuration class to setup some specific behaviour for current string.
     * This is useful when string used inside of ImGui#InputText*() methods.
     */
    public final ImGuiInputTextData inputData = new ImGuiInputTextData();

    byte[] data;
    private String text = "";

    /**
     * Creates an {@link ImString} instance with {@link ImString#DEFAULT_LENGTH} size for the inner buffer.
     */
    public ImString() {
        this(DEFAULT_LENGTH);
    }

    /**
     * Creates an {@link ImString} instance with provided size for the inner buffer.
     * @param length size of the inner buffer to use
     */
    public ImString(final int length) {
        data = new byte[length + CARET_LEN];
    }

    /**
     * Creates an {@link ImString} instance from provided string.
     * Inner buffer size will be equal to the length of the string + {@link ImString#CARET_LEN}.
     * @param text string to create a new {@link ImString}
     */
    public ImString(final String text) {
        set(text, true, 0);
    }

    /**
     * Create an {@link ImString} instance from provided string with custom size for the inner buffer.
     * @param text string to a create a new {@link ImString}
     * @param length custom size for the inner buffer
     */
    public ImString(final String text, final int length) {
        this(length);
        set(text);
    }

    public String get() {
        if (inputData.isDirty) {
            inputData.isDirty = false;
            text = new String(data, 0, inputData.size, StandardCharsets.UTF_8);
        }
        return text;
    }

    public void set(final String value) {
        set(value, inputData.isResizable, inputData.resizeFactor);
    }

    public void set(final String value, final boolean resize) {
        set(value, resize, inputData.resizeFactor);
    }

    public void set(final String value, final boolean resize, final int resizeValue) {
        final byte[] valueBuff = value.getBytes();
        final int currentLen = data == null ? 0 : data.length;
        byte[] newBuff = null;

        // If provided value require a bigger buffer and we can resize it
        if (resize && (currentLen - CARET_LEN) < valueBuff.length) {
            newBuff = new byte[valueBuff.length + resizeValue + CARET_LEN];
            inputData.size = valueBuff.length;
        }

        // If there were no resize and we still need a new buffer
        if (newBuff == null) {
            newBuff = new byte[currentLen];
            inputData.size = Math.max(0, Math.min(valueBuff.length, currentLen - CARET_LEN));
        }

        System.arraycopy(valueBuff, 0, newBuff, 0, Math.min(valueBuff.length, newBuff.length - CARET_LEN));
        data = newBuff;
        inputData.isDirty = true;
    }

    public void resize(final int newSize) {
        if (newSize < data.length) {
            throw new IllegalArgumentException("New size should be greater than current size of the buffer");
        }

        final int size = newSize + CARET_LEN;
        final byte[] newBuffer = new byte[size];
        System.arraycopy(data, 0, newBuffer, 0, data.length);
        data = newBuffer;
    }

    byte[] resizeInternal(final int newSize) {
        resize(newSize + inputData.resizeFactor);
        return data;
    }

    /**
     * Get the length of the text inside of the data buffer.
     * @return length of the text inside of the data buffer
     */
    public int getLength() {
        return get().length();
    }

    /**
     * Get the size of the data buffer. Buffer size will always have '+1' to its size, since it's used by the Dear ImGui to draw a caret char.
     * @return size of the data buffer
     */
    public int getBufferSize() {
        return data.length;
    }

    @Override
    public String toString() {
        return get();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ImString imString = (ImString) o;
        return Objects.equals(text, imString.text);
    }

    @Override
    public int hashCode() {
        return text.hashCode();
    }
}
