import bcrypt from "bcrypt";
import { findUserByEmail } from "../services/userService.js";
import { loginBodySchema, loginResponseSchema } from "../utils/schemas.js";

export default async function (fastify) {
    fastify.post(
        "/authenticate",
        {
            schema: {
                body: loginBodySchema,
                response: { 200: loginResponseSchema },
            },
        },
        async (request, reply) => {
            const user = await findUserByEmail(request.body.email);

            if (!user) throw new Error("Invalid email or password");

            const isValidPassword = bcrypt.compareSync(
                request.body.password,
                user.password
            );

            if (!isValidPassword) throw new Error("Invalid email or password");

            const token = fastify.jwt.sign({ id: user.id });

            const responseSerialization = reply.getSerializationFunction(200);
            return responseSerialization({ access_token: token });
        }
    );
}
