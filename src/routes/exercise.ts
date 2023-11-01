import { BodyPart } from "@prisma/client";
import { FastifyInstance } from "fastify";
import { ExerciseSchema } from "../schemas/exercise";
import { findAllExercises } from "../services/exercise";

export default async function (fastify: FastifyInstance) {
    fastify.get<{ Querystring: { bodyPart: BodyPart } }>(
        "/",
        {
            schema: {
                response: {
                    200: { type: "array", ExerciseSchema },
                },
            },
        },
        async (req, rep) => {
            const { bodyPart } = req.query;

            const exercises = await findAllExercises(bodyPart);
            rep.status(200).send(exercises);
        }
    );
}
