import {BodyPart} from "@prisma/client";
import {FastifyInstance} from "fastify";
import {ExerciseSchema} from "../schemas/exerciseSchema";
import {findAllExercises} from "../services/exerciseService";

export default async function (fastify: FastifyInstance) {
    fastify.get<{ Querystring: { bodyPart: BodyPart[] } }>(
        "/",
        {
            schema: {
                querystring: {
                    type: "object",
                    properties: {
                        bodyPart: {
                            type: "array",
                            items: {type: "string"},
                        },
                    },
                },
                response: {
                    200: {type: "array", ExerciseSchema},
                },
            },
        },
        async (req, rep) => {
            const {bodyPart} = req.query;

            const exercises = await findAllExercises(bodyPart);
            rep.status(200).send(exercises);
        }
    );
}
