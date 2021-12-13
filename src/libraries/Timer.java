package libraries;

import java.time.Instant;
import java.time.Duration;

import resources.DisplaySettings;

public class Timer
{

	private static Instant startOfLoop;

	/**
	 * This method allows us to pause the game for a parametrised time. The pausing
	 * time depends of how old the previous call to beginTimer() is. This way, the
	 * FPS can be kept constant.
	 */
	public static void waitToMaintainConstantFPS()
	{
		int durationLoopMs = (int) Duration.between(startOfLoop, Instant.now()).toMillis();
		int timeToPauseMs = DisplaySettings.MILLISECONDS_PER_FRAME_TO_MAINTAIN_FPS - durationLoopMs;
		if (timeToPauseMs > 0)
		{
			StdDraw.pause(timeToPauseMs);
		}
		else
		{
			boolean print = false;
			if (print)
			{
				System.out.println("Warning, your main loop took " + durationLoopMs + "ms to run (max for "
						+ DisplaySettings.FRAME_PER_SECOND + "fps is "
						+ DisplaySettings.MILLISECONDS_PER_FRAME_TO_MAINTAIN_FPS + ")");
			}
		}
	}

	/**
	 * Initialise the timer.
	 */
	public static void beginTimer()
	{
		startOfLoop = Instant.now();
	}
}
