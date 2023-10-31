import bcrypt from "bcrypt";
import { FastifyInstance } from "fastify";
import { findByEmail } from "../repository/user";
import { LoginType } from "../schemas/user";

export const authenticate = async (
    fastify: FastifyInstance,
    data: LoginType
): Promise<string> => {
    const user = await findByEmail(data.email);

    if (!user) throw new Error("Invalid email or password");

    const isValidPassword = bcrypt.compareSync(data.password, user.password);

    if (!isValidPassword) throw new Error("Invalid email or password");

    return fastify.jwt.sign({ id: user.id });
};
