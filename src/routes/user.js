import bcrypt from "bcrypt";
import {
    createAccountBodySchema,
    createAccountResponseSchema,
} from "../utils/schemas.js";
import { createUser, findUserByEmail } from "../services/userService.js";

export default async function (fastify) {
    fastify.post(
        "/register",
        {
            schema: {
                body: createAccountBodySchema,
                response: { 201: createAccountResponseSchema },
            },
        },
        async (request, reply) => {
            const emailExists = await findUserByEmail(request.body.email);

            if (emailExists) throw new Error("Email already registered");

            const user = await createUser({
                email: request.body.email,
                password: bcrypt.hashSync(request.body.password, 10),
                client: {
                    create: {
                        name: request.body.client.name,
                        weight: request.body.client.weight,
                        height: request.body.client.height,
                    },
                },
            });

            const responseSerialization = reply.getSerializationFunction(201);
            return responseSerialization(user);
        }
    );
}
