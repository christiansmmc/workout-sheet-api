import bcrypt from "bcrypt";
import { create, findByEmail, findById } from "../repository/user.js";

export const createUser = async (userData) => {
    const emailExists = findByEmail(userData.email);

    if (emailExists) throw new Error("Email already registered");

    return await create({
        email: userData.email,
        password: bcrypt.hashSync(userData.password, 10),
        client: {
            create: {
                name: userData.client.name,
                weight: userData.client.weight,
                height: userData.client.height,
                ClientHistory: {
                    create: {
                        weight: userData.client.weight,
                        height: userData.client.height,
                    },
                },
            },
        },
    });
};

export const findUserById = async (id) => await findById(id);
