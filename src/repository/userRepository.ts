import prisma from "../config/prisma";
import { CreateUserType } from "../schemas/userSchema";

export const create = async (data: CreateUserType) =>
    await prisma.user.create({ data, include: { client: true } });

export const findByEmail = async (email: string) =>
    await prisma.user.findUnique({ where: { email } });

export const findById = async (id: string) =>
    await prisma.user.findUnique({ where: { id }, include: { client: true } });
