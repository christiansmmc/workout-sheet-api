import bcrypt from "bcrypt";
import { FastifyInstance } from "fastify";
import { findByEmail } from "../repository/userRepository";
import { LoginType } from "../schemas/userSchema";

export const authenticate = async (
    fastify: FastifyInstance,
    data: LoginType
): Promise<string> => {
    const user = await findByEmail(data.email);

    if (!user) throw new Error("Email ou senha invalido");

    const isValidPassword = bcrypt.compareSync(data.password, user.password);

    if (!isValidPassword) throw new Error("Email ou senha invalido");

    return fastify.jwt.sign({ id: user.id });
};
