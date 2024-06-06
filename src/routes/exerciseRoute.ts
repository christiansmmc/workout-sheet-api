import { BodyPart } from "@prisma/client";
import { FastifyInstance } from "fastify";
import { ExerciseSchema } from "../schemas/exerciseSchema";
import { findAllExercises } from "../services/exerciseService";

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
            console.log("DATABASE_URL: ", process.env.DATABASE_URL);

            const { bodyPart } = req.query;

            const exercises = await findAllExercises(bodyPart);
            rep.status(200).send(exercises);
        }
    );
}
