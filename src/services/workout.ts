import { findByUserId } from "../repository/client";
import { create as createClientExerciseHistory } from "../repository/clientExerciseHistory";
import {
    create,
    deleteById,
    findAllByClientId,
    findById,
} from "../repository/workout";
import {
    create as createWorkoutExercise,
    deleteAllByWorkoutId,
} from "../repository/workoutExercise";
import { CreateCompleteWorkoutType } from "../schemas/workout";

export const createWorkout = async (
    loggedUserId: string,
    data: CreateCompleteWorkoutType
) => {
    const client = await findByUserId(loggedUserId);
    const workout = await create({ name: data.name, client_id: client.id });
    data.workoutExercises.forEach((workoutExercise) => {
        createWorkoutExercise({
            load: workoutExercise.load,
            exercise_id: workoutExercise.exercise_id,
            workout_id: workout.id,
        });

        createClientExerciseHistory({
            load: workoutExercise.load,
            exercise_id: workoutExercise.exercise_id,
            client_id: client.id,
        });
    });

    return workout;
};

export const findAllWorkoutByLoggedUser = async (loggedUserId: string) => {
    const client = await findByUserId(loggedUserId);
    return await findAllByClientId(client.id);
};

export const findWorkoutById = async (loggedUserId: string, id: string) => {
    const client = await findByUserId(loggedUserId);
    const workout = await findById(id);

    if (workout.client_id != client.id) {
        throw new Error("Workout not from logged user");
    }

    return workout;
};

export const deleteWorkoutById = async (loggedUserId: string, id: string) => {
    const client = await findByUserId(loggedUserId);
    const workout = await findById(id);

    if (workout.client_id != client.id) {
        throw new Error("Workout not from logged user");
    }

    await deleteAllByWorkoutId(workout.id);
    await deleteById(workout.id);
};
