import bcrypt from "bcrypt";
import { create, findByEmail, findById } from "../repository/userRepository";
import { CreateAccountType } from "../schemas/userSchema";

export const createUser = async (data: CreateAccountType) => {
    const emailExists = await findByEmail(data.email);

    if (emailExists) throw new Error("Email already registered");

    return await create({
        email: data.email,
        password: bcrypt.hashSync(data.password, 10),
        client: {
            create: {
                name: data.client.name,
                weight: data.client.weight,
                height: data.client.height,
                clientRecords: {
                    create: {
                        weight: data.client.weight,
                        height: data.client.height,
                    },
                },
            },
        },
    });
};

export const findUserById = async (id: string) => await findById(id);
