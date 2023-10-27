import bcrypt from "bcrypt";
import { findByEmail } from "../repository/user.js";

export const authenticate = async (fastify, loginData) => {
    const user = await findByEmail(loginData.email);

    if (!user) throw new Error("Invalid email or password");

    const isValidPassword = bcrypt.compareSync(
        loginData.password,
        user.password
    );

    if (!isValidPassword) throw new Error("Invalid email or password");

    return fastify.jwt.sign({ id: user.id });
};
