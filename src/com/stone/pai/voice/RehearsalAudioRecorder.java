package com.stone.pai.voice;

import com.stone.common.StoneLog;

import android.media.MediaRecorder;

public class RehearsalAudioRecorder {
	/**
	 * INITIALIZING : recorder is initializing; READY : recorder has been initialized, recorder not yet started RECORDING : recording ERROR : reconstruction
	 * needed STOPPED: reset needed
	 */
	public enum State {
		INITIALIZING, READY, RECORDING, ERROR, STOPPED
	};

	public static final boolean RECORDING_UNCOMPRESSED = true;
	public static final boolean RECORDING_COMPRESSED = false;

	protected static final String TAG = "RehearsalAudioRecorder";

	// Recorder used for compressed recording
	private MediaRecorder mRecorder = null;

	// Output file path
	private String fPath = null;

	// Recorder state; see State
	private State state;

	/**
	 * 
	 * Returns the state of the recorder in a RehearsalAudioRecord.State typed object. Useful, as no exceptions are thrown.
	 * 
	 * @return recorder state
	 */
	public State getState() {
		return state;
	}

	/**
	 * 
	 * 
	 * Default constructor
	 * 
	 * Instantiates a new recorder, in case of compressed recording the parameters can be left as 0. In case of errors, no exception is thrown, but the state is
	 * set to ERROR
	 * 
	 */
	public RehearsalAudioRecorder(boolean uncompressed, int audioSource, int sampleRate, int channelConfig, int audioFormat) {
		try {
			mRecorder = new MediaRecorder();
			mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
			mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
			mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

			fPath = null;
			state = State.INITIALIZING;
		}
		catch (Exception e) {
			if (e.getMessage() != null) {
				StoneLog.e(RehearsalAudioRecorder.class.getName(), e, e.getMessage());
			}
			else {
				StoneLog.e(RehearsalAudioRecorder.class.getName(), e, "Unknown error occured while initializing recording");
			}
			state = State.ERROR;
		}
	}

	/**
	 * Sets output file path, call directly after construction/reset.
	 * 
	 * @param output
	 *            file path
	 * 
	 */
	public void setOutputFile(String argPath) {
		try {
			if (state == State.INITIALIZING) {
				fPath = argPath;
				mRecorder.setOutputFile(fPath);
			}
		}
		catch (Exception e) {
			if (e.getMessage() != null) {
				StoneLog.e(RehearsalAudioRecorder.class.getName(), e, e.getMessage());
			}
			else {
				StoneLog.e(RehearsalAudioRecorder.class.getName(), e, "Unknown error occured while setting output path");
			}
			state = State.ERROR;
		}
	}

	/**
	 * 
	 * Returns the largest amplitude sampled since the last call to this method.
	 * 
	 * @return returns the largest amplitude since the last call, or 0 when not in recording state.
	 * 
	 */
	public int getMaxAmplitude() {
		if (state == State.RECORDING) {
			try {
				return mRecorder.getMaxAmplitude();
			}
			catch (IllegalStateException e) {
				return 0;
			}
		}
		else {
			return 0;
		}
	}

	/**
	 * 
	 * Prepares the recorder for recording, in case the recorder is not in the INITIALIZING state and the file path was not set the recorder is set to the ERROR
	 * state, which makes a reconstruction necessary. In case uncompressed recording is toggled, the header of the wave file is written. In case of an
	 * exception, the state is changed to ERROR
	 * 
	 */
	public void prepare() {
		try {
			if (state == State.INITIALIZING) {
				mRecorder.prepare();
				state = State.READY;
			}
			else {
				StoneLog.w(RehearsalAudioRecorder.class.getName(), "prepare() method called on illegal state");
				release();
				state = State.ERROR;
			}
		}
		catch (Exception e) {
			if (e.getMessage() != null) {
				StoneLog.e(RehearsalAudioRecorder.class.getName(), e, e.getMessage());
			}
			else {
				StoneLog.e(RehearsalAudioRecorder.class.getName(), e, "Unknown error occured in prepare()");
			}
			state = State.ERROR;
		}
	}

	/**
	 * 
	 * 
	 * Releases the resources associated with this class, and removes the unnecessary files, when necessary
	 * 
	 */
	public void release() {
		if (state == State.RECORDING) {
			stop();
		}

		if (mRecorder != null) {
			mRecorder.release();
		}
	}

	/**
	 * 
	 * 
	 * Resets the recorder to the INITIALIZING state, as if it was just created. In case the class was in RECORDING state, the recording is stopped. In case of
	 * exceptions the class is set to the ERROR state.
	 * 
	 */
	public void reset() {
		try {
			if (state != State.ERROR) {
				release();
				fPath = null; // Reset file path
				mRecorder = new MediaRecorder();
				mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
				mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
				mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
				state = State.INITIALIZING;
			}
		}
		catch (Exception e) {
			StoneLog.e(RehearsalAudioRecorder.class.getName(), e, e.getMessage());
			state = State.ERROR;
		}
	}

	/**
	 * 
	 * 
	 * Starts the recording, and sets the state to RECORDING. Call after prepare().
	 * 
	 */
	public void start() {
		if (state == State.READY) {
			mRecorder.start();
			state = State.RECORDING;
		}
		else {
			StoneLog.w(RehearsalAudioRecorder.class.getName(), "start() called on illegal state");
			state = State.ERROR;
		}
	}

	/**
	 * 
	 * 
	 * Stops the recording, and sets the state to STOPPED. In case of further usage, a reset is needed. Also finalizes the wave file in case of uncompressed
	 * recording.
	 * 
	 */
	public void stop() {
		if (state == State.RECORDING) {
			mRecorder.stop();

			state = State.STOPPED;
		}
		else {
			StoneLog.w(RehearsalAudioRecorder.class.getName(), "stop() called on illegal state");
			state = State.ERROR;
		}
	}

	/*
	 * 
	 * Converts a byte[2] to a short, in LITTLE_ENDIAN format
	 */
	private short getShort(byte argB1, byte argB2) {
		return (short) (argB1 | (argB2 << 8));
	}
}
