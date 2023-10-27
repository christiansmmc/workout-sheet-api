import prisma from "../config/prisma.js";

export const create = async (data) =>
    prisma.user.create({ data, include: { client: true } });

export const findByEmail = async (email) =>
    prisma.user.findUnique({ where: { email } });

export const findById = async (id) => prisma.user.findUnique({ where: { id } });
