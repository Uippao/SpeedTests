; count.asm
BITS 64
SECTION .data
buf times 32 db 0
newline db 10

SECTION .bss
start_time resq 2
end_time   resq 2

SECTION .text
global _start

SYS_WRITE equ 1
SYS_EXIT  equ 60
SYS_CLOCK_GETTIME equ 228
CLOCK_MONOTONIC equ 1
STDOUT equ 1

_start:
    ; Get start time
    mov rax, SYS_CLOCK_GETTIME
    mov rdi, CLOCK_MONOTONIC
    lea rsi, [start_time]
    syscall

    ; Print 1 to 10000
    mov rcx, 1
.loop:
    cmp rcx, 10001
    jge .get_end_time
    mov rdi, rcx
    call print_int
    call print_newline
    inc rcx
    jmp .loop

.get_end_time:
    ; Get end time
    mov rax, SYS_CLOCK_GETTIME
    mov rdi, CLOCK_MONOTONIC
    lea rsi, [end_time]
    syscall

    ; Calculate elapsed time
    mov rax, [end_time]
    sub rax, [start_time]
    mov rbx, [end_time+8]
    sub rbx, [start_time+8]

    ; Print seconds (rax)
    mov rdi, rax
    call print_int
    call print_newline

    ; Print nanoseconds (rbx)
    mov rdi, rbx
    call print_int
    call print_newline

    ; Exit
    mov rax, SYS_EXIT
    xor rdi, rdi
    syscall

; Print integer in RDI
print_int:
    mov rax, rdi
    lea rsi, [buf + 31]
    mov byte [rsi], 10
    dec rsi
.pi_loop:
    xor rdx, rdx
    mov rbx, 10
    div rbx
    add dl, '0'
    mov [rsi], dl
    dec rsi
    test rax, rax
    jnz .pi_loop
    inc rsi
    mov rax, SYS_WRITE
    mov rdi, STDOUT
    lea rdx, [buf + 32]
    sub rdx, rsi
    syscall
    ret

print_newline:
    mov rax, SYS_WRITE
    mov rdi, STDOUT
    lea rsi, [newline]
    mov rdx, 1
    syscall
    ret
