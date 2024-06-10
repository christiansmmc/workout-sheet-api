import {FastifyInstance} from "fastify";
import {CreateCompleteWorkoutType, CreateUpdateWorkoutLoadType, UpdateWorkoutNameType,} from "../schemas/workoutSchema";
import {
    addExerciseInWorkout,
    createWorkout,
    deleteWorkoutById,
    findAllWorkoutByLoggedUser,
    findWorkoutById,
    removeExerciseFromWorkout,
    updateLoadFromExercise,
    updateWorkoutName,
} from "../services/workoutService";

export default async function (fastify: FastifyInstance) {
    fastify.get<{ Params: { id: string } }>(
        "/:id",
        {onRequest: [fastify.authenticate]},
        async (req, rep) => {
            const {id} = req.params;
            const workout = await findWorkoutById(req.user.id, id);
            rep.status(200).send(workout);
        }
    );

    fastify.get(
        "/",
        {onRequest: [fastify.authenticate]},
        async (req, rep) => {
            const workouts = await findAllWorkoutByLoggedUser(req.user.id);
            rep.status(200).send(workouts);
        }
    );

    fastify.post<{ Body: CreateCompleteWorkoutType }>(
        "/",
        {onRequest: [fastify.authenticate]},
        async (req, rep) => {
            const workout = await createWorkout(req.user.id, req.body);
            rep.status(201).send(workout);
        }
    );

    fastify.post<{
        Body: CreateUpdateWorkoutLoadType;
        Params: { workoutId: string; exerciseId: string };
    }>(
        "/:workoutId/exercises/:exerciseId",
        {onRequest: [fastify.authenticate]},
        async (req, rep) => {
            const {workoutId, exerciseId} = req.params;
            const response = await addExerciseInWorkout(
                req.user.id,
                workoutId,
                exerciseId,
                req.body.load
            );
            rep.status(201).send(response);
        }
    );

    fastify.patch<{
        Body: CreateUpdateWorkoutLoadType;
        Params: { workoutId: string; exerciseId: string };
    }>(
        "/:workoutId/exercises/:exerciseId",
        {onRequest: [fastify.authenticate]},
        async (req, rep) => {
            const {workoutId, exerciseId} = req.params;
            const response = await updateLoadFromExercise(
                req.user.id,
                workoutId,
                exerciseId,
                req.body.load
            );
            rep.status(200).send(response);
        }
    );

    fastify.patch<{
        Body: UpdateWorkoutNameType;
        Params: { id: string };
    }>(
        "/:id",
        {onRequest: [fastify.authenticate]},
        async (req, rep) => {
            const {id} = req.params;
            const response = await updateWorkoutName(req.user.id, id, req.body);
            rep.status(200).send(response);
        }
    );

    fastify.delete<{ Params: { id: string } }>(
        "/:id",
        {onRequest: [fastify.authenticate]},
        async (req, rep) => {
            const {id} = req.params;
            await deleteWorkoutById(req.user.id, id);
            rep.status(204).send();
        }
    );

    fastify.delete<{ Params: { workoutId: string; exerciseId: string } }>(
        "/:workoutId/exercises/:exerciseId",
        {onRequest: [fastify.authenticate]},
        async (req, rep) => {
            const {workoutId, exerciseId} = req.params;
            await removeExerciseFromWorkout(req.user.id, workoutId, exerciseId);
            rep.status(204).send();
        }
    );
}
