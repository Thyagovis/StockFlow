import { Search } from "lucide-react"
import { Input } from "@/shared/ui/input"

function getFormattedDate(): string {
  return new Intl.DateTimeFormat("pt-BR", {
    weekday: "long",
    day: "numeric",
    month: "long",
    year: "numeric",
  }).format(new Date())
}

export function DashboardHeader() {

  return (
    <div className="flex flex-col gap-4 sm:flex-row sm:items-start sm:justify-between">
      <div>
        <p className="mt-0.5 text-sm capitalize text-muted-foreground">
          {getFormattedDate()}
        </p>
      </div>

      <div className="relative w-full max-w-xs">
        <Search className="pointer-events-none absolute left-3 top-1/2 h-4 w-4 -translate-y-1/2 text-muted-foreground" />
        <Input
          placeholder="Pesquisa global..."
          className="pl-9"
          aria-label="Pesquisa global (em breve)"
          readOnly
        />
      </div>
    </div>
  )
}
