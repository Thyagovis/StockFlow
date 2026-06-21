import { z } from "zod"

const baseUserSchema = z.object({
  nome: z.string().min(2, "Informe o nome completo"),
  email: z.string().min(1, "Informe o e-mail").email("E-mail inválido"),
  login: z.string().min(1, "Informe o login"),
  cargo: z.enum(
      ["ADMINISTRADOR", "GERENTE", "SUPERVISOR", "ENCARREGADO"],
      {
        errorMap: () => ({ message: "Selecione um cargo" }),
      },
  ),
  ativo: z.boolean().default(true),
})

export const createUserSchema = baseUserSchema.extend({
  senha: z.string().min(6, "A senha deve ter ao menos 6 caracteres"),
})

export const updateUserSchema = baseUserSchema.extend({
  senha: z
      .string()
      .refine(
          (value) => value === "" || value.length >= 6,
          {
            message: "A senha deve ter ao menos 6 caracteres",
          },
      ),
})

const userFormSchema = baseUserSchema.extend({
  senha: z.string(),
})

export type UserFormValues = z.infer<typeof userFormSchema>