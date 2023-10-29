import { authenticate } from "../services/auth.js";
import { loginBodySchema, loginResponseSchema } from "../utils/schemas.js";

export default async function (fastify) {
    fastify.post(
        "/api/authenticate",
        {
            schema: {
                body: loginBodySchema,
                response: { 200: loginResponseSchema },
            },
        },
        async (req, rep) => {
            const token = await authenticate(fastify, req.body);

            const responseSerialization = rep.getSerializationFunction(200);
            return responseSerialization({ access_token: token });
        }
    );
}
