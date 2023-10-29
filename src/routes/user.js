import { createUser, findUserById } from "../services/user.js";
import {
    createAccountBodySchema,
    createAccountResponseSchema,
    getUserResponseSchema,
} from "../utils/schemas.js";

export default async function (fastify) {
    fastify.post(
        "/api/user",
        {
            schema: {
                body: createAccountBodySchema,
                response: { 201: createAccountResponseSchema },
            },
        },
        async (req, rep) => {
            const user = await createUser(req.body);

            const responseSerialization = rep.getSerializationFunction(201);
            return responseSerialization(user);
        }
    );

    fastify.get(
        "/api/user",
        {
            onRequest: [fastify.authenticate],
            schema: {
                response: { 200: getUserResponseSchema },
            },
        },
        async (req, rep) => {
            const user = await findUserById(req.user.id);

            const responseSerialization = rep.getSerializationFunction(200);
            return responseSerialization(user);
        }
    );
}
