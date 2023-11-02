import { FastifyInstance } from "fastify";
import {
    CreateCompleteWorkoutSchema,
    CreateCompleteWorkoutType,
    FindAllWorkoutSchema,
    FindWorkoutByIdSchema,
    WorkoutIdSchema,
} from "../schemas/workout";
import {
    createWorkout,
    deleteWorkoutById,
    findAllWorkoutByLoggedUser,
    findWorkoutById,
} from "../services/workout";

export default async function (fastify: FastifyInstance) {
    fastify.post<{ Body: CreateCompleteWorkoutType }>(
        "/",
        {
            onRequest: [fastify.authenticate],
            schema: {
                body: CreateCompleteWorkoutSchema,
                response: {
                    201: WorkoutIdSchema,
                },
            },
        },
        async (req, rep) => {
            const workout = await createWorkout(req.user.id, req.body);
            rep.status(201).send(workout);
        }
    );

    fastify.get<{ Params: { id: string } }>(
        "/:id",
        {
            onRequest: [fastify.authenticate],
            schema: {
                response: {
                    200: FindWorkoutByIdSchema,
                },
            },
        },
        async (req, rep) => {
            const { id } = req.params;
            const workout = await findWorkoutById(req.user.id, id);
            rep.status(200).send(workout);
        }
    );

    fastify.get(
        "/",
        {
            onRequest: [fastify.authenticate],
            schema: {
                response: {
                    200: FindAllWorkoutSchema,
                },
            },
        },
        async (req, rep) => {
            const workouts = await findAllWorkoutByLoggedUser(req.user.id);
            rep.status(200).send(workouts);
        }
    );

    fastify.delete<{ Params: { id: string } }>(
        "/:id",
        {
            onRequest: [fastify.authenticate],
        },
        async (req, rep) => {
            const { id } = req.params;
            await deleteWorkoutById(req.user.id, id);
            rep.status(204).send();
        }
    );
}
