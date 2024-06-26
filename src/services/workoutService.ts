import {create as createClientExerciseHistory} from "../repository/clientExerciseRecordRepository";
import {findByUserId} from "../repository/clientRepository";
import {
    create as createWorkoutExercise,
    deleteAllByWorkoutId,
    deleteByWorkoutAndExercise,
    findByWorkoutAndExercise,
    updateLoad,
} from "../repository/workoutExerciseRepository";
import {create, deleteById, findAllByClientId, findById, updateName,} from "../repository/workoutRepository";
import {CreateCompleteWorkoutType, UpdateWorkoutNameType,} from "../schemas/workoutSchema";
import {findById as findExercisebyId} from "../repository/exerciseRepository"

export const createWorkout = async (
    loggedUserId: string,
    data: CreateCompleteWorkoutType
) => {
    const client = await findByUserId(loggedUserId);
    const workout = await create({name: data.name, clientId: client.id});
    for (const workoutExercise of data.workoutExercises) {
        const exercise = await findExercisebyId(workoutExercise.exerciseId)

        await createWorkoutExercise({
            load: workoutExercise.load !== undefined && workoutExercise.load !== null ? workoutExercise.load : 0,
            exerciseId: exercise.id,
            workoutId: workout.id,
        });

        if (workoutExercise.load !== undefined && workoutExercise.load !== null) {
            await createClientExerciseHistory({
                load: workoutExercise.load,
                exerciseId: exercise.id,
                clientId: client.id,
            });
        }
    }

    return workout;
};

export const findAllWorkoutByLoggedUser = async (loggedUserId: string) => {
    const client = await findByUserId(loggedUserId);
    return await findAllByClientId(client.id);
};

export const findWorkoutById = async (loggedUserId: string, id: string) => {
    const client = await findByUserId(loggedUserId);
    const workout = await findById(id);

    if (workout.clientId != client.id) {
        throw new Error("Workout not from logged user");
    }

    return workout;
};

export const deleteWorkoutById = async (loggedUserId: string, id: string) => {
    const client = await findByUserId(loggedUserId);
    const workout = await findById(id);

    if (workout.clientId != client.id) {
        throw new Error("Workout not from logged user");
    }

    await deleteAllByWorkoutId(workout.id);
    await deleteById(workout.id);
};

export const removeExerciseFromWorkout = async (
    loggedUserId: string,
    workoutId: string,
    exerciseId: string
) => {
    const client = await findByUserId(loggedUserId);
    await deleteByWorkoutAndExercise(workoutId, exerciseId, client.id);
};

export const updateLoadFromExercise = async (
    loggedUserId: string,
    workoutId: string,
    exerciseId: string,
    load: any
) => {
    const client = await findByUserId(loggedUserId);
    const workoutExercise = await findByWorkoutAndExercise(
        workoutId,
        exerciseId,
        client.id
    );

    if (workoutExercise.load != load) {
        await createClientExerciseHistory({
            load: load,
            exerciseId: exerciseId,
            clientId: client.id,
        });

        await updateLoad(workoutId, exerciseId, client.id, load);
    }

    return {id: workoutId};
};

export const addExerciseInWorkout = async (
    loggedUserId: string,
    workoutId: string,
    exerciseId: string,
    load: any
) => {
    const client = await findByUserId(loggedUserId);
    const workout = await findById(workoutId);

    if (workout.clientId != client.id) {
        throw new Error("Workout not from logged user");
    }

    await createWorkoutExercise({
        load: load,
        exerciseId: exerciseId,
        workoutId: workoutId,
    });

    return workout;
};

export const updateWorkoutName = async (
    loggedUserId: string,
    id: string,
    data: UpdateWorkoutNameType
) => {
    const client = await findByUserId(loggedUserId);
    const workout = await findById(id);

    if (workout.clientId != client.id) {
        throw new Error("Workout not from logged user");
    }

    await updateName(id, data);
};
