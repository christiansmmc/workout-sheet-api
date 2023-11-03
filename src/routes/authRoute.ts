import { FastifyInstance } from "fastify";
import {
    LoginResponseSchema,
    LoginSchema,
    LoginType,
} from "../schemas/userSchema";
import { authenticate } from "../services/authService";

export default async function (fastify: FastifyInstance) {
    fastify.post<{ Body: LoginType }>(
        "/",
        {
            schema: {
                body: LoginSchema,
                response: {
                    200: LoginResponseSchema,
                },
            },
        },
        async (req, rep) => {
            const token = await authenticate(fastify, req.body);
            rep.status(200).send({ access_token: token });
        }
    );
}
