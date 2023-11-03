import { FastifyInstance } from "fastify";
import {
    CreateAccountResponseSchema,
    CreateAccountSchema,
    CreateAccountType,
    GetUserResponseSchema,
} from "../schemas/userSchema";
import { createUser, findUserById } from "../services/userService";

export default async function (fastify: FastifyInstance) {
    fastify.post<{ Body: CreateAccountType }>(
        "/",
        {
            schema: {
                body: CreateAccountSchema,
                response: { 201: CreateAccountResponseSchema },
            },
        },
        async (req, rep) => {
            const user = await createUser(req.body);
            rep.status(201).send(user);
        }
    );

    fastify.get(
        "/",
        {
            onRequest: [fastify.authenticate],
            schema: {
                response: { 200: GetUserResponseSchema },
            },
        },
        async (req, rep) => {
            const user = await findUserById(req.user.id);
            rep.status(200).send(user);
        }
    );
}
